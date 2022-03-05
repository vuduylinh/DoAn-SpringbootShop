function drawLikeColumnChart() {
    var url = "http://localhost:8080/api/product/like/10";
    fetch(url).then(resp => resp.json()).then(json => {
        var config = {
            id: "column-chart-like",
            options: {
                chart: {
                    title: 'HÀNG YÊU THÍCH NHẤT',
                    subtitle: 'Các mặt hàng được yêu thích nhất',
                },
                legend: {
                    position: 'none'
                }
            },
            data: []
        }
        config.data.push(['Hàng hóa', 'Lượt thích'], ...json.map(e => [e.group, e.count]));
        drawColumnChart(config);
    });
}

function drawShareColumnChart() {
    var url = "http://localhost:8080/api/product/share/10";
    fetch(url).then(resp => resp.json()).then(json => {
        var config = {
            id: "column-chart-share",
            options: {
                chart: {
                    title: 'HÀNG CHIA SẺ NHIỀU NHẤT',
                    subtitle: 'Các mặt hàng được chia sẻ nhiều nhất',
                },
                legend: {
                    position: 'none'
                }
            },
            data: []
        }
        config.data.push(['Hàng hóa', 'Lượt chia sẻ'], ...json.map(e => [e.group, e.count]));
        drawColumnChart(config);
    });
}

function drawInventoryCharts() {
    var url = "http://localhost:8080/api/inventory";
    fetch(url).then(resp => resp.json()).then(json => {
        var config = {
            id: "pie-chart-value",
            options: {
                title: "KIỂM KÊ KHO HÀNG THEO GIÁ TRỊ",
				is3D: true
            },
            data: []
        }
        config.data.push(['Loại', 'Tổng giá trị'], ...json.map(e => [e.group, e.sum]));
        drawPieChart(config);

        var config = {
            id: "pie-chart-quantity",
            options: {
                title: "KIỂM KÊ KHO HÀNG THEO SỐ LƯỢNG",
				is3D: true
            },
            data: []
        }
        config.data.push(['Loại', 'Tổng số lượng'], ...json.map(e => [e.group, e.count]));
        drawPieChart(config);

        var config = {
            id: "line-chart-price",
            options: {
                title: 'BIỂU ĐỒ GIÁ',
                curveType: 'function',
                legend: {
                    position: 'bottom'
                }
            },
            data: []
        }
        config.data.push(['Loại', 'Thấp nhất', 'Trung Bình', 'Cao nhất'], ...json.map(e => [e.group, e.min, e.avg, e.max]));
        drawLineChart(config);

        var config = {
            id: "column-chart-price",
            options: {
                chart: {
                    title: 'BIỂU ĐỒ GIÁ',
                    subtitle: 'Thấp nhất, Trung Bình và Cao nhất',
                },
                legend: {
                    position: 'none'
                }
            },
            data: []
        }
        config.data.push(['Loại', 'Thấp nhất', 'Trung bình', 'Cao nhất'], ...json.map(e => [e.group, e.min, e.avg, e.max]));
        drawColumnChart(config);
    });
}

function drawAllCharts() {
    drawLikeColumnChart();
    drawShareColumnChart();
    drawInventoryCharts();
}