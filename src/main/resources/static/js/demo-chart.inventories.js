google.charts.load('current', { 
    'packages': ['corechart'] });
google.charts.setOnLoadCallback(drawAllCharts);

        function drawAllCharts() {
            drawChart1();
            drawChart2();
            window.addEventListener("resize", function(){
                drawChart1();
                drawChart2();
            })
        }

        function drawChart1() {
            var url = "http://localhost:8080/api/product/like/10";
            fetch(url).then(resp => resp.json()).then(json => {

                var config = {
                    id: 'curve_chart1',
                    options: {
                        title: 'Doanh so ban hang',
                        curveType: 'function',
                        legend: { position: 'bottom' }
                    },
                    data: [
                        ['Sản Phẩm', 'Lượt Thích'],
                        // ['2004', 1000],
                        // ['2005', 1170],
                        // ['2006', 660],
                        // ['2007', 1030]
                    ]
                }
                var mang = json.map(e => [e.group, e.count]);
                console.log(mang);
                config.data.push(...mang);
                drawLineChart(config);
            })

        }


        function drawChart2() {
            var config = {
                id: 'curve_chart2',
                options: {
                    is3D: true,
                },
                data: [
                    ['Year', 'Sales'],
                    ['2004', 1000],
                    ['2005', 1170],
                    ['2006', 660],
                    ['2007', 1030]
                ]

            }
            drawPieChart(config);
        }
      