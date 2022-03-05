google.charts.load('current', {
    'packages': ['corechart', 'bar']
});

google.charts.setOnLoadCallback(function() {
    drawAllCharts();
    window.addEventListener("resize", function() {
        drawAllCharts();
    });
});

function drawAllCharts() {}

function drawPieChart(config) {
    var dataTable = google.visualization.arrayToDataTable(config.data);
    var pieChart = new google.visualization.PieChart(document.getElementById(config.id));
    pieChart.draw(dataTable, config.options);
}

function drawLineChart(config) {
    var dataTable = google.visualization.arrayToDataTable(config.data);
    var lineChart = new google.visualization.LineChart(document.getElementById(config.id));
    lineChart.draw(dataTable, config.options);
}

function drawColumnChart(config) {
    var dataTable = google.visualization.arrayToDataTable(config.data);
    var barChart = new google.charts.Bar(document.getElementById(config.id));
    barChart.draw(dataTable, google.charts.Bar.convertOptions(config.options));
}