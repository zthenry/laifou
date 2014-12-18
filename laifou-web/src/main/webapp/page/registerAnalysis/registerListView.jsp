<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/page/base.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>广告位管理</title>

<script src="${contextPath}/resource/script/jquery.pagination.js" type="text/javascript"></script>
<script type="text/javascript">
	var pageSign = false;
	$(function(){
		
		
		
	});
	
    
    function nextPage(page){
    	$("#curPage").val(page);
    	$("#search").submit();
    }
    
    function updateFriends(type,id){
    	var url = "";
    	if(type==1){
    		url="${contextPath}/register/updateFirstDimesion/"+id;
    	}
    	else if(type==2){
    		url="${contextPath}/register/updateSecondDimesion/"+id;
    	}
    	
    	$.ajax({
				type:"get",
				url:url,
				dataType:"json",
				success:function(data){
					if(data.success){
						alert("操作成功");	
					}else{
						alert("操作失败");
					}
					
		    		
				}
		 });
    }
</script>
</head>

<body>
<div class="content">
        
        <ul class="breadcrumb">
            <li>数据管理》注册用户 </li>
        </ul>
        <div class="container-fluid">
            <div class="row-fluid">
					<div class="search-well">
						<form class="form-inline"
							action="${contextPath}/register" method="post" id="search">
							手机号码:
							<select name="appClientId" id="appClientId" class="input-medium" onchange="getVersion()">
								<option value="0">
									请选择
								</option>
								
							</select>
							注册时间 起始：
							<select name="appClientVersion" id="appClientVersion" class="input-small" onchange="getAdspaceNum()">
								<option value="0">
									请选择
								</option>
								
							</select>
							结束：
							<select name="adSpaceId" id="adSpaceId" class="input-small" >
								<option value="0">
									请选择
								</option>
								
							</select>
							<button class="btn" type="submit">
								<i class="icon-search"></i> 搜索
							</button>
						</form>
						
					</div>
					
			 </div>
			<div class="well">
				<table id="sample-table-1" class="table table-bordered table-hover">
					<tr>
						<td width="10%" align="center" bgcolor="#EEEEEE">
							<span class="tabletitle">ID</span>
						</td>
						<td width="10%" align="center" bgcolor="#EEEEEE">
							<span class="tabletitle">手机号码</span>
						</td>
						<td width="20%" align="center" bgcolor="#EEEEEE">
							<span class="tabletitle">注册时间</span>
						</td>
						<td width="15%" align="center" bgcolor="#EEEEEE">
							<span class="tabletitle">状态</span>
						</td>
						
						<td width="30%" align="center" bgcolor="#EEEEEE">
							<span class="tabletitle">操作</span>
						</td>
					</tr>
					<c:forEach var="register" items="${registerPhones}" varStatus="status">
						<tr>
							<td>${register.id}</td>
							<td>${register.phone}</td>
							<td><fmt:formatDate value="${register.registTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td>${register.status}</td>
							<td>
								<a class="btn btn-mini" href="${contextPath}/adspace/toEditAdSpace?adSpaceId=${adspace.id}">查看用户信息</a>
								<a class="btn btn-mini" href="javascript:updateFriends(1,${register.id})">一度朋友</a>
								<a class="btn btn-mini" href="javascript:updateFriends(2,${register.id})">二度朋友</a>
							</td>
						</tr>
					</c:forEach>
				</table>
				<div class="pagination"></div>
			</div>
			
</div>
</div>
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