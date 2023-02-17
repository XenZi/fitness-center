const pulse = document.querySelector(".pulse");
const calories = document.querySelector(".calories");
let isSessionActive = true;
const btnEndSession = document.querySelector(".btn-end-session");
const refetchIntervalID = setInterval(() => {
    getSessionData(`?calories=${calories.textContent.split(" ")[1]}`);
}, 1500);

const getSessionData = async (query) => {
    fetch("http://localhost:8000" + query)
        .then((res) => res.json())
        .then((data) => {
            pulse.innerHTML = `Pulse: ${data.pulse}`;
            calories.innerHTML = `Calories: ${data.calories}`;
        });
};

const resendRequest = () => {
    if (!isSessionActive) clearInterval(refetchIntervalID);
    else {
        refetchIntervalID();
    }
};

const changeSessionStatusToInactive = () => {
    isSessionActive = false;
};

const getParamValue = () => {
    const location = decodeURI(window.location.toString());
    const index = location.indexOf("?") + 1;
    const substrings = location.substring(index, location.length);
    const splitedSubstring = substrings.split("=");
    return splitedSubstring[1]
};

btnEndSession.addEventListener("click", () => {
    changeSessionStatusToInactive();
    resendRequest();
    let formData = new FormData();
    let caloriesValue = calories.textContent.split(" ")[1];
    let pulseValue = pulse.textContent.split(" ")[1];
    console.log({caloriesValue, pulseValue});
    formData.append('calories', caloriesValue + "");
    formData.append('pulse', pulseValue);
    formData.append('sessionID', getParamValue());
    fetch('http://localhost:8080/training-session/end', {
        method: "POST",
        body: formData
    })}
);

getSessionData("");
resendRequest();
