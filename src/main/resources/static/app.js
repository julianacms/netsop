/**
 * Common
*/
//#region Common
const formatter = new Intl.NumberFormat('no-NB', {
  style: 'decimal', // 'currency' places the currency symbol at the start. Using 'decimal' to manually place it at the end. Also makes it reusable for weight.
  maximumFractionDigits: 0,
  notation: 'standard',
});

const formatPrice = (price) => `${formatter.format(price)} kr`;
const formatWeight = (weight) => `${formatter.format(weight)} kg`;
//#endregion

/**
 * Price overview
*/
//#region Price overview
const priceOverview = document.getElementById('price-overview-body');

const getPriceOverview = async () => {
  const res = await fetch('/api/prices');
  
  if (!res.ok) {
    return;
  }

  const data = await res.json();

  const rows = data.prices
    .reduce((acc, { weight, quantity, price }) => {
      const existingRow = acc[weight];

      if (existingRow === undefined) {
        acc[weight] = [
          createColumnHeader(weight),
          createPriceCell(price),
        ];
      } else {
        existingRow[quantity] = createPriceCell(price);
      }

      return acc;
    }, [])
    .map(createRow);

    rows.forEach((row) => priceOverview.appendChild(row));
};

const createColumnHeader = (weight) => {
  const columnHeader = document.createElement('td');
  columnHeader.classList.add('price-header');
  columnHeader.textContent = formatWeight(weight);
  return columnHeader;
};

const createPriceCell = (price) => {
  const priceCell = document.createElement('td');
  priceCell.classList.add('price-cell');
  priceCell.textContent = formatPrice(price);
  return priceCell;
};

const createRow = (columns) => {
  const row = document.createElement('tr');
  row.classList.add('price-row');
  columns.forEach((column) => row.appendChild(column));
  return row;
};

getPriceOverview();
//#endregion

/**
 * Calculator
*/
//#region Calculator
//#region Elements
const priceOutput = document.getElementById('price');
const totalWeightOutput = document.getElementById('total-weight');
const quantityInput = document.getElementById('quantity');
const weightInput = document.getElementById('weight');
const weightContainer = document.getElementById('weight-container');
//#endregion

//#region Calculator state
let weight = 1;
let quantity = 1;
let error = false;
//#endregion

//#region Error message
const errorMessage = document.createElement('p');
errorMessage.classList.add('error');
errorMessage.textContent = 'Weight caanot be higher than 35 kg.';
//#endregion

const getPrice = async () => {
  priceOutput.textContent = '-';
  
  // Reset error message if it exists.
  if (error) {
    // Remove error message.
    weightContainer.removeChild(errorMessage);
    error = false;
  }
  
  const res = await fetch(`/api/price?weight=${weight}&quantity=${quantity}`);
  
  if (!res.ok) {
    // Status code 400 is returned if the weight is higher than 35 kg.
    if (res.status === 400) {
      // Show error message.
      weightContainer.appendChild(errorMessage);
      error = true;
    }

    return;
  }

  const price = await res.json();

  priceOutput.textContent = formatPrice(price);
};

//#region Event listeners
weightInput.addEventListener('input', (e) => {
  const parsed = parseInputEvent(e);
  
  if (parsed === null) {
    weightInput.value = weight;
    return;
  }

  weight = parsed;
  updateTotalWeight();
  getPrice();
});

quantityInput.addEventListener('input', (e) => {
  const parsed = parseInputEvent(e);

  if (parsed === null) {
    quantityInput.value = quantity;
    return;
  }

  quantity = parsed;
  updateTotalWeight();
  getPrice();
});
//#endregion

const updateTotalWeight = () => {
  totalWeightOutput.textContent = formatWeight(weight * quantity);
};

/** Parses the value of the input element from its event. */
const parseInputEvent = (e) => {
  const value = e.target.value;
  const parsed = Number.parseInt(value, 10);
  
  if (Number.isNaN(parsed) || parsed < 1) {
    return null;
  }

  return parsed;
}

// Get initial price.
getPrice();
//#endregion