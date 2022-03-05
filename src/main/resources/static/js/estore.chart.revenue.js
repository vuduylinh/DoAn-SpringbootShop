function drawProductLineChart() {
    var url = "http://localhost:8080/api/revenue/product";
    fetch(url).then(resp => resp.json()).then(json => {
        var config = {
            id: "line-chart-product",
            options: {
                title: 'DOANH THU THEO SẢN PHẨM',
                curveType: 'function',
                legend: {
                    position: 'bottom'
                }
            },
            data: []
        }
        config.data.push(['Sản Phẩm', 'Doanh Thu'], ...json.map(e => [e.group, e.sum]));
        drawLineChart(config);
    });
}

function drawCategoryPieChart() {
    var url = "http://localhost:8080/api/revenue/category";
    fetch(url).then(resp => resp.json()).then(json => {
        var config = {
            id: "pie-chart-category",
            options: {
                title: "DOANH THU THEO DANH MỤC",
				is3D: true
            },
            data: []
        }
        config.data.push(['Danh mục', 'Doanh Thu'], ...json.map(e => [e.group, e.sum]));
        drawPieChart(config);
    });
}

function drawCustomerLineChart() {
    var url = "http://localhost:8080/api/revenue/customer";
    fetch(url).then(resp => resp.json()).then(json => {
        var config = {
            id: "line-chart-customer",
            options: {
                title: 'DOANH THU THEO KHÁCH HÀNG',
                curveType: 'function',
                legend: {
                    position: 'bottom'
                }
            },
            data: []
        }
        config.data.push(['Khách hàng', 'Doanh thu'], ...json.map(e => [e.group, e.sum]));
        drawLineChart(config);
    });
}

function drawYearColumnChart() {
    var url = "http://localhost:8080/api/revenue/year";
    fetch(url).then(resp => resp.json()).then(json => {
        var config = {
            id: "column-chart-year",
            options: {
                chart: {
                    title: 'DOANH THU THEO NĂM',
                    subtitle: 'Doanh số bán hàng theo năm',
                },
                legend: {
                    position: 'none'
                }
            },
            data: []
        }
        config.data.push(['Năm', 'Doanh thu'], ...json.map(e => [`${e.group}`, e.sum]));
        drawColumnChart(config);
    });
}

function drawQuarterPieChart() {
    var url = "http://localhost:8080/api/revenue/quarter";
    fetch(url).then(resp => resp.json()).then(json => {
        var config = {
            id: "pie-chart-quarter",
            options: {
                title: "DOANH THU THEO QUÝ",
				is3D: true
            },
            data: []
        }
        config.data.push(['Quý', 'Doanh thu'], ...json.map(e => [`Q${e.group}`, e.sum]));
        drawPieChart(config);
    });
}

function drawMonthColumnChart() {
    var url = "http://localhost:8080/api/revenue/month";
    fetch(url).then(resp => resp.json()).then(json => {
        var config = {
            id: "column-chart-month",
            options: {
                chart: {
                    title: 'DOANH THU THEO THÁNG',
                    subtitle: 'Doanh số bán hàng theo tháng',
                },
                legend: {
                    position: 'none'
                }
            },
            data: []
        }
        config.data.push(['Tháng', 'Doanh thu'], ...json.map(e => [`M${e.group}`, e.sum]));
        drawColumnChart(config);
    });
}

function drawAllCharts() {
    drawProductLineChart();
    drawCategoryPieChart();
    drawCustomerLineChart();
    drawYearColumnChart();
    drawQuarterPieChart();
    drawMonthColumnChart();
}