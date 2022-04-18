export const onlyLetters = "This field can only contain letters!";
export const onlyNumbers = "This field can only contain numbers!";

const onlyLettersRegex = new RegExp('^[a-zA-Z]+$');
const onlyNumbersRegex = new RegExp('^[0-9]+$');

// Samo sam kopirala od negde, proveriti da li ok
const validEmail = new RegExp(
    '^[a-zA-Z0-9._:$!%-]+@[a-zA-Z0-9.-]+.[a-zA-Z]$'
 );
const validPassword = new RegExp('^(?=.*?[A-Za-z])(?=.*?[0-9]).{6,}$');

export function checkLettersInput(input){
    return onlyLettersRegex.test(input);     
}

export function checkNumInput(input){
    return onlyNumbersRegex.test(input);     
}

export function capitalizeString(string) {
    return string.charAt(0).toUpperCase() + string.slice(1).toString().toLowerCase();
  }