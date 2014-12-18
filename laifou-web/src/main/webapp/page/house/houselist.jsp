<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/page/base.jsp" %>

<!DOCTYPE html>
<html>
<head>
<title>房产信息管理</title>

<script src="${contextPath}/resource/script/jquery.pagination.js" type="text/javascript"></script>
<style type="text/css">
	.cover {
			position:fixed; top: 0px; right:0px; bottom:0px;filter: alpha(opacity=60); background-color: #777;
			z-index: 1002; left: 0px; display:none;opacity:0.5; -moz-opacity:0.5;
	}
</style>
<script type="text/javascript">
	var pageSign = false;
	$(function(){
		
		$("#beginDate").datetimepicker({
             changeYear : true,
             showSecond: true,
             regional : "zh-CN",
             dateFormat : "yy-mm-dd",
             timeFormat: "HH:mm:ss"
           });
		 
		 $("#endDate").datetimepicker({
             changeYear : true,
             showSecond: true,
             regional : "zh-CN",
             dateFormat : "yy-mm-dd",
             timeFormat: "HH:mm:ss"
           });
		
	});
	
    
    function nextPage(page){
    	$("#currentPage").val(page);
    	submitform();
    }
    
    function rebuildIndex(){
    	var result = confirm("确定重新构建所有索引？");
    	
    	if(result){
    		showMask();
    		$.ajax({
  				type:"get",
  				url:"${contextPath}/house/indexAllRebuild",
  				dataType:"json",
  				success:function(data){
  					showMask();
  					if(data.success==0){
  						
  						alert("操作成功");
  						submitform();
  					}
  		    		
  				}
  		   });
    	}
    }
    
    function mergeNewIndex(){
    	var result = confirm("确定添加新数据索引？");
    	
    	if(result){
    		showMask();
    		$.ajax({
  				type:"get",
  				url:"${contextPath}/house/mergeNewindex",
  				dataType:"json",
  				success:function(data){
  					showMask();
  					if(data.success==0){
  						
  						alert("操作成功");
  						submitform();
  					}
  		    		
  				}
  		   });
    	}
    }
    
    function showMask(){
    	//$('body').css("overflow","hidden");
    	$("#cover").toggle();
    }
    
    function addIndex(id){
    	var result = confirm("确定重新构建该记录的索引？");
    	
    	if(result){
    		showMask();	
    		$.ajax({
  				type:"get",
  				url:"${contextPath}/house/addIndex/"+id,
  				dataType:"json",
  				success:function(data){
  					showMask();
  					if(data.success==0){
  						alert("操作成功");
  						submitform();
  					}
  		    		
  				}
  		   });
    	}
    }
    
    function deleteIndex(id){
    	var result = confirm("确定删除该记录的索引？");
    	
    	if(result){
    		showMask();	
    		$.ajax({
  				type:"get",
  				url:"${contextPath}/house/deleteLuceneIndex/"+id,
  				dataType:"json",
  				success:function(data){
  					showMask();
  					if(data.success==0){
  						alert("操作成功");
  						submitform();
  					}
  		    		
  				}
  		   });
    	}
    }
    function submitform(){
		var houseId = $("#houseId").val();
		
		if(houseId==''){
			$("#houseId").val("0");
		}
		
		$("#condition").submit();
	}
</script>
</head>

<body>
<div class="content">
        
        <ul class="breadcrumb">
            <li>搜索管理》房产信息</li>
        </ul>
        <div class="container-fluid">
            <div class="row-fluid">
					<div class="search-well">
						<form class="form-inline"
							action="${contextPath}/house/price/condition" method="POST" id="condition">
							<input type="hidden" name="currentPage" id="currentPage" value="1">
							城市:
							<select name="cityName" id="cityName" class="input-medium">
								<option value="西安">
									西安
								</option>
								
							</select>
							ID:
							<c:if test="${condition.houseId==0}">
								<input name="houseId" id="houseId" type="text" class="input-medium" value="" />
							</c:if>
							<c:if test="${condition.houseId>0}">
								<input name="houseId" id="houseId" type="text" class="input-medium" value="${condition.houseId}" />
							</c:if>
							起始时间：
							<input name="beginDate" id="beginDate" type="text" class="input-ylarge" value="<fmt:formatDate value="${condition.beginDate}" pattern="yyyy-MM-dd HH:mm:ss" type="date"/>" />
								
							结束时间：
							<input name="endDate" id="endDate" type="text" class="input-ylarge"  value="<fmt:formatDate value="${condition.endDate}" pattern="yyyy-MM-dd HH:mm:ss" type="date"/>" />
								
							<button class="btn" type="submit" onclick="submitform()">
								<i class="icon-search"></i> 搜索
							</button>
						</form>
						<div class="btn-toolbar">
								<button class="btn btn-primary" onclick="rebuildIndex();">
									索引重建
								</button>
								<button class="btn btn-primary" onclick="mergeNewIndex();">
									添加新数据索引
								</button>
								<div class="btn-group">
								</div>
							</div>
					</div>
					
			 </div>
			<div class="well">
				<table id="sample-table-1" class="table table-bordered table-hover">
					<tr>
						<td width="5%" align="center" bgcolor="#EEEEEE">
							<span class="tabletitle">ID</span>
						</td>
						<td width="6%" align="center" bgcolor="#EEEEEE">
							<span class="tabletitle">城市</span>
						</td>
						<td width="10%" align="center" bgcolor="#EEEEEE">
							<span class="tabletitle">收录时间</span>
						</td>
						
						<td width="20%" align="center" bgcolor="#EEEEEE">
							<span class="tabletitle">开发商</span>
						</td>
						<td width="15%" align="center" bgcolor="#EEEEEE">
							<span class="tabletitle">楼盘</span>
						</td>
						
						<td width="20%" align="center" bgcolor="#EEEEEE">
							<span class="tabletitle">地址</span>
						</td>
						<td width="7%" align="center" bgcolor="#EEEEEE">
							<span class="tabletitle">价格</span>
						</td>
						<td width="7%" align="center" bgcolor="#EEEEEE">
							<span class="tabletitle">lucene索引</span>
						</td>
						
						<td width="10%" align="center" bgcolor="#EEEEEE">
							<span class="tabletitle">操作</span>
						</td>
					</tr>
					<c:forEach var="h" items="${houses}" varStatus="status">
						<tr>
							<td>${h.id}</td>
							<td>${h.cityName}</td>
							<td><fmt:formatDate value="${h.createTime}" pattern="yyyy-MM-dd"/></td>
							<td>${h.kfsName}</td>
							<td>${h.houseName}</td>
							<td>${h.address}</td>
							<td>${h.price}</td>
							<td>
								<c:if test="${h.indexBuild==1}">
									YES
								</c:if>
								<c:if test="${h.indexBuild==0}">
									NO
								</c:if>
							</td>
							<td>
								<a class="btn btn-mini" href="${contextPath}/house/statis?housename=${h.houseName}">价格趋势</a>
								
								<a class="btn btn-mini" href="javascript:addIndex(${h.id})">建立索引</a>
								
								<a class="btn btn-mini" href="javascript:deleteIndex(${h.id})">删除索引</a>
								
							</td>
						</tr>
					</c:forEach>
				</table>
				<div class="pagination"></div>
			</div>
			
</div>
</div>
<div id="cover" class="cover"></div>
<script type='text/javascript'>
        var options = {
            currentPage: '${page.curPage}',
            totalPages: '${page.pageCount}',
	    	numberOfPages:'${page.pageSize}',
	    	alignment:'left',
	    	pageUrl: function(type, page, current){
				return "javascript:nextPage("+page+")";  
            }
        }
        
        $('.pagination').bootstrapPaginator(options);
    </script><span style="font-family:'sans serif, tahoma, verdana, helvetica';"><span style="white-space:normal;"> </span></span>
</body>
</html>