# hadoop-mysql
海量Web日志分析 用Hadoop提取KPI统计指标，写入mysql


access_20160913.log
122.190.88.148 - - [12/Sep/2016:00:01:09 +0800] "POST /Hxkq/Report HTTP/1.1" 200 31 "-" "HXClockIn/1.0.13 (iPhone; iOS 9.3.5; Scale/3.00)" - 119.97.211.26:8887
192.168.13.44 - - [12/Sep/2016:00:01:15 +0800] "GET /Pc/index/getmyauditcount/type/1?_=1473575769487 HTTP/1.1" 200 381 "http://10.0.0.161:8080/Pc/Index/index" "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36" - 10.0.0.161:8080
192.168.66.250 - - [12/Sep/2016:00:01:15 +0800] "GET /Pc/index/getmyauditcount/type/1?_=1473562138645 HTTP/1.1" 200 409 "http://10.0.0.161:8080/pc/index/index" "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36" - 10.0.0.161:8080
172.20.67.32 - - [12/Sep/2016:00:01:24 +0800] "GET /pc/login/login HTTP/1.1" 200 6948 "-" "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; Trident/6.0)" - 10.0.0.161:8080

urlcount2建表语句
CREATE TABLE `urlcount2` (
	`url` VARCHAR(50) NULL DEFAULT NULL,
	`count` INT(11) NULL DEFAULT NULL
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;
