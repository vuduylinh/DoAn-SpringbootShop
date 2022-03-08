
function drawLineChart(config) {
    var data = google.visualization.arrayToDataTable(config.data);
    var chart = new google.visualization.LineChart(document.getElementById(config.id));
    chart.draw(data, config.options);
}

function drawPieChart(config) {
    var data = google.visualization.arrayToDataTable(config.data);
    var chart = new google.visualization.PieChart(document.getElementById(config.id));
    chart.draw(data, config.options);
}

