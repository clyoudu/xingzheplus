<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>行者+</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="shortcut icon" href="../statics/img/favicon.png"/>
    <link rel="bookmark" href="../statics/img/favicon.png"/>
    <!-- site css -->
    <link rel="stylesheet" href="../statics/boot-flat/css/site.min.css">
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,800,700,400italic,600italic,700italic,800italic,300italic" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="//at.alicdn.com/t/font_gcyk6xk4l5jt6gvi.css">
    <script type="text/javascript" src="../statics/boot-flat/js/site.min.js"></script>
    <script type="text/javascript" src="../statics/js/xinzheplus.js"></script>
    <script type="text/javascript" src="../statics/js/echarts.js"></script>
    <style>
        .sub-list{
            padding-left: 20px;
        }
        .list-group-item:hover{
            background-color: #4FC1E9;
            color: #FFF;
        }
        .ct{
            margin-top: 60px;
        }
        .navbar{
            z-index: 99999;
        }
        .bg{
            font-size: 150%;
        }
        .footer-logo a{
            font-weight: normal;
        }
    </style>
</head>
<body>
<!--nav-->
<nav role="navigation" class="navbar navbar-custom navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button data-target="#bs-content-row-navbar-collapse-5" data-toggle="collapse" class="navbar-toggle" type="button">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="${base}/index" class="navbar-brand"><i class="iconfont icon-xingzhe bg"></i><i class="fa fa-plus"></i></a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div id="bs-content-row-navbar-collapse-5" class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="https://www.imxingzhe.com/" target="_blank"><i class="iconfont icon-xingzhe"></i> </a></li>
                <li class="dropdown">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">${username!'CL有毒'} <b class="caret"></b></a>
                    <ul role="menu" class="dropdown-menu">
                        <li><a href="${base}/crawler">数据抓取</a></li>
                        <li class="divider"></li>
                        <li><a href="${base}/logout">退出</a></li>
                    </ul>
                </li>
            </ul>

        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<!--header-->

<div class="container-fluid ct">
    <!--documents-->
    <div class="row row-offcanvas row-offcanvas-left" style="margin-left: 0">
        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" role="navigation">
            <ul class="list-group panel">
                <li class="list-group-item"><img height="50px" class="img-circle" src="http://static.imxingzhe.com/949201/1501513208.jpg!avatar"> <b>CL有毒</b></li>
                <li class="list-group-item"><i class="fa fa-bicycle"></i> <b>整体</b></li>
                <li class="list-group-item"><a href="${base}/index"><i class="glyphicon glyphicon-dashboard sub-list"></i>Dashboard </a></li>
                <li class="list-group-item"><a href="${base}/crawler"><i class="glyphicon glyphicon-refresh sub-list"></i>数据同步 </a></li>
                <li class="list-group-item"><i class="fa fa-pie-chart"></i> <b>图表</b></li>
                <li class="list-group-item"><a href="${base}/distance"><i class="glyphicon glyphicon-road sub-list"></i> 里程</a></li>
                <li class="list-group-item"><a href="${base}/index"><i class="glyphicon glyphicon-time sub-list"></i> 时间</a></li>
                <li class="list-group-item"><a href="${base}/index"><i class="glyphicon glyphicon-flash sub-list"></i> 速度</a></li>
                <li class="list-group-item"><a href="${base}/index"><i class="glyphicon glyphicon-heart sub-list"></i> 心率</a></li>
                <li class="list-group-item"><a href="${base}/index"><i class="glyphicon glyphicon-screenshot sub-list"></i> 踏频</a></li>
                <li class="list-group-item"><a href="${base}/index"><i class="glyphicon glyphicon-map-marker sub-list"></i> 位置</a></li>
                <li class="list-group-item"><a href="${base}/index"><i class="glyphicon glyphicon-calendar sub-list"></i> 月视图</a></li>
                <li class="list-group-item"><a href="${base}/index"><i class="glyphicon glyphicon-adjust sub-list"></i> 能力</a></li>
                <li class="list-group-item"><a href="${base}/index"><i class="glyphicon glyphicon-cloud sub-list"></i> 海拔</a></li>
                <li class="list-group-item"><a href="${base}/durationDistance"><i class="fa fa-line-chart sub-list"></i> 时间里程分布</a></li>
            </ul>
        </div>