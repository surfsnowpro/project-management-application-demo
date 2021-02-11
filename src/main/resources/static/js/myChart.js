const chartDataStr = decodeHtml(chartData);
const chartJsonArray = JSON.parse(chartDataStr);

const arrayLength = chartJsonArray.length;
const labelData = [];
const valueData = [];

for (let i = 0; i < arrayLength; i++) {
    valueData[i] = chartJsonArray[i].value
    labelData[i] = chartJsonArray[i].label
}

new Chart(document.getElementById("myPieChart"), {
    type: 'pie',
    data: {
        labels: labelData,
        datasets: [{
            label: 'Project Status',
            backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f"],
            borderColor: 'rgb(255, 99, 132)',
            data: valueData
        }]
    },
    options: {
        title: {
            display: true,
            text: 'Project Statuses'
        }
    }
});

// [{"value": 1, "label": "COMPLETED}. {"value": 2, "label": "INPROGRESS"}...]
function decodeHtml(html) {
    var txt = document.createElement("textarea");
    txt.innerHTML = html;
    return txt.value;
}