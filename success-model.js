
function showSuccessModal(email){

document.getElementById(
"successEmailMsg"
).innerHTML=

`We have sent your request to the Mechanic and sent you an email on <strong>${email}</strong> to track status of your request.` ;

document.getElementById(
"successModal"
).classList.add("show");
}

function closeSuccessModal(){

document.getElementById(
"successModal"
).classList.remove("show");
}