const amount = document.getElementById("amount");
const submitButton = document.getElementById("submitButton");
function clearInput() {
  amount.value = 0;
}
submitButton.addEventListener("click", clearInput);
