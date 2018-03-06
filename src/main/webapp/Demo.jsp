<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
        <style type="text/css">
            *{
                margin: 0px;
                padding: 0px;
            }
            .filePicker{
                width: 160px;
                height: 44px;
                line-height: 44px;
                text-align: center;
                color: #fff;
                background: #00b7ee;        
            }
            .filePicker input[type="file"] {
                position: relative;
                top: -44px;
                left: 0px;
                width: 160px;
                height: 44px;
                opacity: 0;
                cursor: pointer;
                overflow: hidden;
                z-index: 0;
            }
            
            .container{
                width: 160px;
                margin: 30px auto;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <input type="file" name="" id="" value="" />
        </div> 
        <form action="" method = "post" enctype="multipart/form-data" id ="uploadForm">
	        <div class="container">
	            <div class="filePicker">
	                <label>click</label>
	                <input id="fileInput" type="file" name="file" multiple="multiple" accept=".csv, application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">
	            </div>
	        </div>
        </form>   
    </body>
</html>