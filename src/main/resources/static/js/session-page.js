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

btnEndSession.addEventListener("click", () => {
    changeSessionStatusToInactive();
    resendRequest();
});

getSessionData("");
resendRequest();
