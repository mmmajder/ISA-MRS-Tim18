export const onlyLetters = "This field can only contain letters!";
export const onlyNumbers = "This field can only contain numbers!";
export const deletionReason = "Please input reason for your deletion request"

const onlyLettersRegex = new RegExp('^([a-zA-Z]+\\s)*[a-zA-Z]+$');
const onlyNumbersRegex = new RegExp('^[0-9]+$');

// Samo sam kopirala od negde, proveriti da li ok
const validEmail = new RegExp(
    '^[a-zA-Z0-9._:$!%-]+@[a-zA-Z0-9.-]+.[a-zA-Z]$'
 );
const validPassword = new RegExp('^(?=.*?[A-Za-z])(?=.*?[0-9]).{6,}$');
const validPositiveNumber = new RegExp('[1-9][0-9]*');

// allowed space between words and after
export function checkLettersInput(input){
    input = removeSpaces(input)
    return onlyLettersRegex.test(input);
}

export function checkEmailInput(input){
    return validEmail.test(input);
}

export function checkPasswordInput(input){
    return validPassword.test(input);
}


export function checkNumInput(input){
    return onlyNumbersRegex.test(input);     
}


export function isEmpty(input){
    if(input ===null || input === undefined || input ==='' ){
        return false;
    }
    return true;
}


function removeSpaces(input){
    return input.trim().split(/ +/).join(' ');
}

export function isPercentageNumber(input) {
    return isPositiveNumber(input) && (input>0 && input<100)
}

export function isPositiveNumber(input) {
    return (typeof input=='number')
}

/*function capitalizeString(string) {
    return string.charAt(0).toUpperCase() + string.slice(1).toString().toLowerCase();
}

function uniformString(string) {
    return string.charAt(0) + string.slice(1).toString().toLowerCase();
}

function capitalizeWords(string) {
    const words = string.split(/ +/);
    for (let index = 0; index < words.length; index++) {
        words[index] = capitalizeString(words[index]);
    }
    return words.join(' ')
}*/
