<#include "head.ftl"/>
<div class="col-xs-12 col-sm-9 content">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title"><a href="javascript:void(0);" class="toggle-sidebar"><span class="fa fa-angle-double-left" data-toggle="offcanvas" title="Maximize Panel"></span></a>数据同步 </h3>
        </div>
        <div class="panel-body">
            <div class="content-row">
                <div class="row">
                    <div class="col-sm-1"></div>
                    <div class="col-sm-2">
                        <select name="selecter_basic" class="selecter_3" data-selecter-options='{"cover":"true"}' id="y1">
                            <option value="2017">2017</option>
                            <option value="2016">2016</option>
                            <option value="2015">2015</option>
                            <option value="2014">2014</option>
                            <option value="2013">2013</option>
                            <option value="2012">2012</option>
                        </select>
                    </div>

                    <div class="col-sm-2">
                        <select name="selecter_basic" class="selecter_3" data-selecter-options='{"cover":"true"}' id="m1">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                        </select>
                    </div>

                    <div class="col-sm-2 text-center"><span style="font-size: 150%"><i class="fa  fa-arrows-h"></i></span></div>

                    <div class="col-sm-2">
                        <select name="selecter_basic" class="selecter_3" data-selecter-options='{"cover":"true"}' id="y2">
                            <option value="2017">2017</option>
                            <option value="2016">2016</option>
                            <option value="2015">2015</option>
                            <option value="2014">2014</option>
                            <option value="2013">2013</option>
                            <option value="2012">2012</option>
                        </select>
                    </div>

                    <div class="col-sm-2">
                        <select name="selecter_basic" class="selecter_3" data-selecter-options='{"cover":"true"}' id="m2">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                        </select>
                    </div>
                    <div class="col-sm-1"></div>
                </div>
                <div class="row">
                    <div class="col-sm-1"></div>
                    <div class="col-sm-10">
                        <div class="input-group">
                            <div class="input-group-btn">
                                <button tabindex="-1" class="btn btn-primary" type="button">SessionId</button>
                            </div>
                            <input type="text" class="form-control" id="sid">
                        </div>
                    </div>
                    <div class="col-sm-1"></div>

                </div>
                <div class="row">
                    <div class="col-sm-1"></div>
                    <div class="col-sm-10">
                        <button type="button" class="btn btn-primary btn-block" id="s-c">开始同步</button>
                    </div>
                    <div class="col-sm-1"></div>

                </div>
                <div class="row">
                    <div class="col-sm-1"></div>
                    <div class="col-sm-10">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">日志</h3>
                            </div>
                            <div class="panel-body" style="min-height: 300px" id="lg">
                                Panel content
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-1"></div>
                </div>
            </div>
        </div><!-- panel body -->
    </div>
</div><!-- content -->
<#include "footer.ftl" />