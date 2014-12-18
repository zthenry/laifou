<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/page/base.jsp" %>

<!DOCTYPE html>
<html>
<head>
<title>房产信息管理</title>
<script src="${contextPath}/resource/script/jquery/autoComplete/jquery.ui.core.js"></script>
<script src="${contextPath}/resource/script/jquery/autoComplete/jquery.ui.widget.js"></script>
<script src="${contextPath}/resource/script/jquery/autoComplete/jquery.ui.position.js"></script>
<script src="${contextPath}/resource/script/jquery/autoComplete/jquery.ui.autocomplete.js"></script>

<script type="text/javascript">
	
	$(function(){
		
		$( "#housename" ).autocomplete({
            source: function( request, response ) {
                $.ajax({
                	type:"POST",
                    url: "${contextPath}/house/searchHousename?housename=" + request.term,
                    dataType: "json",
                    success: function( data ) {
                    	
                    	response($.map(data.housenames, function(item) {
                            return {
                                value: item
                            }
                        }));
                    }
                });
            },
            minLength: 1,
            select: function( event, ui ) {
                $("#housename").val(ui.item.value);
                
            }
        });
		
		
	});
	
	
	function autoCompleteInput()  
	{  
	    $("#housename").autocomplete({  
	        source: function(request, response){
	        	var housename = $("#housename").val();
	        	
	        	$.ajax({
	        		type: "POST",
	        		url: "${contextPath}/house/searchHousename?housename=" + encodeURI(encodeURI(housename)),
	        		dataType: "json",
	        		success:function(data) {
	        			if(data.success==0){
	        				response($.map(data.housenames, function(item) {
	        					alert(item);
	                            return {
	                                label: item,
	                                value: iteme
	                            }
	                        }));
	        			}
	        		}
	        		});
	        		
	        },
	        minLength:2,  
	        select: function(event,ui){
	        	$("#housename").val(ui.item.name);
	        }  
	    });  
	  
	}
	
	function submitform(){
		var minPrice = $("#minPrice").val();
		var maxPrice = $("#maxPrice").val();
		
		if(minPrice==''){
			$("#minPrice").val("0");
		}
		
		if(maxPrice==''){
			$("#maxPrice").val("0");
		}
		$("#indexCondition").submit();
	}
	
</script>
</head>

<body>
<div class="content">
        
        <ul class="breadcrumb">
            <li>搜索管理》爬虫信息</li>
        </ul>
        <div class="container-fluid">
            <div class="row-fluid">
					<div class="search-well">
						<form class="form-inline"
							action="${contextPath}/house/search" method="POST" id="indexCondition">
							<input type="hidden" name="currentPage" id="currentPage" value="1">
							楼盘名:
							<input name="housename" id="housename" class="input-medium" value="${housename}"/>
							价格区间:
							<c:if test="${minPrice==0}">
								<input name="minPrice" id="minPrice" class="input-medium" placeholder="最低价" value="" />
							</c:if>
							<c:if test="${minPrice>0}">
								<input name="minPrice" id="minPrice" class="input-medium" placeholder="最低价" value="${minPrice}" />
							</c:if>
							～
							<c:if test="${maxPrice==0}">
								<input name="maxPrice" id="maxPrice" class="input-medium" placeholder="最高价" value="" />
							</c:if>
							<c:if test="${maxPrice>0}">
								<input name="maxPrice" id="maxPrice" class="input-medium" placeholder="最高价" value="${maxPrice}" />
							</c:if>
							<button class="btn" type="submit" onclick="submitform()">
								<i class="icon-search"></i> 搜索
							</button>
						</form>
						
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
								<a class="btn btn-mini" href="${contextPath}/house/statis?housename=${h.houseName}">价格趋势</a>
								<br><br>
								<a class="btn btn-mini" href="${contextPath}/house/deleteLuceneIndex/${h.id}">删除索引</a>
							</td>
						</tr>
					</c:forEach>
				</table>
				
			</div>
			
</div>
</div>
</body>
</html>