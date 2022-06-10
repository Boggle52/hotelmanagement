<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="hotelstyle.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Myeongjo&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>
<body>
    <br>&nbsp;&nbsp;&nbsp;&nbsp;<a href="hotelreservation.jsp">�������</a> <a href="hotelroom.jsp">���ǰ���</a>
    <table id="base">
        <tr>
            <td id="border">
                <table id="inner">
                    <tr><td id="right">���ڱⰣ</td><td id="left">&nbsp;&nbsp;<input type="date" id=begindate>~<input type="date" id=enddate></td></tr>
                    <tr><td id="right">�����ο�</td><td id="left">&nbsp;&nbsp;<input type="number" id=person>��</td></tr>
                    <tr><td id="right">��������</td><td id="left">&nbsp;&nbsp;<select id=roomtype></select></td></tr>
                    <tr><td colspan="2" id="button"><input type="button" value="ã��" class="btn btn-secondary" id=find></td></tr>
                    <tr><td colspan="2" id="left">&nbsp;���డ�ɰ���</td></tr>
                    <tr><td colspan="2" id="top">
                    <input type=hidden id='_typenum'>
                    <select size="17" class="form-select" id=availroom></select></td></tr>
                </table>
            </td>
            <td id="border">
                <table id="inner">
                    <tr><td id="right">�����ȣ</td><td><input type="number" id=_booknum readonly></td></tr>
                    <tr><td id="right">���Ǹ�</td><td><input type=text id=_roomname readonly></td></tr>
                    <tr><td id="right">��������</td><td><input type=text id=_roomtype readonly></td></tr>
                    <tr><td id="right">���ڿ����ο�</td><td><input type="number" id=_person></td></tr>
                    <tr><td id="right">���ڱⰣ</td><td><input type="date" id=_begindate readonly>~<input type="date" id=_enddate readonly></td></tr>
                    <tr><td id="right">������</td><td><input type="text" id=name></td></tr>
                    <tr><td id="right">�����</td><td><input type="number" id=mobile placeholder='010 �����ϰ� �Է�'></td></tr>
                    <tr><td id="right">�����Ѿ�</td><td><input type="number" id=total readonly></td></tr>
                    <tr><td colspan="2" id="button">
                    	<input type="hidden" id="optype" value="add">
                    	<input type="button" value="������" class="btn btn-secondary" id=complete>
                    	&nbsp;<input type="button" value="�������" class="btn btn-secondary" id=cancel>
                    	&nbsp;<input type="button" value="����" class="btn btn-secondary" id=remove></td></tr>
                </table>
            </td>
            <td id="border">
                <table id="inner">
                    <tr><td>���೻��</td></tr>
                    <tr><td><select size="23" class="form-select" id=booked></select></td></tr>
                </table>
            </td>
        </tr>
    </table>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<script>
$(document)
.ready(function(){
 	loadTypeData()
 	$('#person').change(function(){
 		if($('#person').val()<1){
 			alert('�߸� �Է��ϼ̽��ϴ�.')
 			$('#person').val('')
 		}
 	})
 	$('#_person').change(function(){
 		if($('#_person').val()<1){
 			alert('�߸� �Է��ϼ̽��ϴ�.')
 			$('#_person').val('')
 		}
 	})
 	$('#enddate').change(function(){
 		if($('#enddate').val()<=$('#begindate').val()){
 			alert('�߸� �Է��ϼ̽��ϴ�.')
 			$('#enddate').val('')
 		}
 	})
 	$('#_enddate').change(function(){
		if($('#_enddate').val()<=$('#_begindate').val()){
			alert('�߸� �Է��ϼ̽��ϴ�.')
			$('#_enddate').val('')
		}
 	})
 	$('#_person').change(function(){
 		if($('#_person').attr("placeholder")!=''){
 			let num = $('#_person').attr("placeholder").charAt(0)
 			parseInt(num)
 			if($('#_person').val()>num){
 				alert('�߸� �Է��ϼ̽��ϴ�.')
 				$('#_person').val('')
 			}
 		}
 	})
})
.on('click','#find',function(){
	if($('#begindate').val()==''||$('#enddate').val()==''||$('#person').val==''
			||$('#roomtype').val()==''){
		alert('���� �Է��ϼ���.')
	}
	$.ajax({
		type:'get',url:'selectavail',dataType:'json',
		data:{begindate:$('#begindate').val(),enddate:$('#enddate').val(),
			person:$('#person').val(),typenum:$('#roomtype').val()},
		success:function(data){
			$('#availroom').empty();
			for(i=0; i<data.length; i++){
				let jo = data[i]
				let str = "<option value="+jo['roomnum']+">"+jo['roomname']+", "
					+jo['roomtype']+", "+jo['person']+"��"+", "+jo['price']+"��</option>"
				$('#availroom').append(str)
			}
			$('#_typenum').val($('#roomtype').val())
		}
	})
	$.ajax({
		type:'get',url:'selectbook',dataType:'json',
		data:{begindate:$('#begindate').val(),enddate:$('#enddate').val(),
			typenum:$('#roomtype option:selected').val()},
		success:function(data){
			$('#booked').empty();
			for(i=0; i<data.length; i++){
				let jo = data[i]
				let str = "<option value="+jo['booknum']+">"+jo['roomname']+", "
					+jo['begindate']+"~"+jo['enddate']+", "+jo['name']+"</option>"
				$('#booked').append(str)
			}
		}
	})
})
.on('click','#complete',function(){
	if($('#_person').val()==''||$('#name').val()==''||$('#mobile').val()==''){
		alert('���� �Է��Ͻʽÿ�.')
	} else if($('#optype').val()=='add'){
		$.ajax({
			type:'get',url:'insertbook',dataType:'text',
			data:{roomnum:$('#availroom option:selected').val(),
				typenum:$('#_typenum').val(),person:$('#_person').val(),
				begindate:$('#_begindate').val(),enddate:$('#_enddate').val(),name:$('#name').val(),
				mobile:$('#mobile').val(),total:$('#total').val()},
			success:function(){
				$('#find').trigger('click')
				$('#remove').trigger('click')
			}
		})
	} else {
		$.ajax({
			type:'get',url:'updatebook',dataType:'json',
			data:{booknum:$('#_booknum').val(),person:$('#_person').val(),name:$('#name').val(),
				mobile:$('#mobile').val()},
		})
		$('#find').delay(500).trigger('click')
		$('#remove').trigger('click')
		$('#optype').val('add')
		$('#complete').val('�����߰�')
		$('#_person').attr("placeholder",'')
	}
})
.on('click','#cancel',function(){
	$.ajax({
		type:'get',url:'deletebook',
		data:'booknum='+$('#booked option:selected').val(),
		success:function(data){
			$('#find').trigger('click')
			$('#remove').trigger('click')
		}
	})
})
.on('click','#remove',function(){
	$('#_booknum,#_roomname,#_roomtype,#_person,#_begindate,#_enddate,#name,#mobile,#total').val('')
	$('#optype').val('add')
	$('#complete').val('�����߰�')
	$('#_person').attr("placeholder",'')
})
.on('click','#availroom option',function(){
	$('#_booknum').val('')
	$('#name').val('')
	$('#mobile').val('')
	$('#_begindate').val($('#begindate').val())
	$('#_enddate').val($('#enddate').val())
	$('#_person').val($('#person').val())
	arr = $('#availroom option:selected').text().split(", ")
	$('#_roomname').val(arr[0])
	$('#_roomtype').val(arr[1])
	$('#_person').val($('#person').val())
	let num = parseInt(arr[2].charAt(0))
	$('#_person').attr("placeholder",+num+"�� ����")
	begindate = new Date($('#begindate').val())
	enddate = new Date($('#enddate').val())
	let diff = Math.ceil((enddate - begindate)/(1000*3600*24))
	$('#total').val(parseInt(arr[3].slice(0,-1))*diff)
	
})
.on('click','#booked option',function(){
	 $.ajax({
		type:'get',url:'selectdetail',dataType:'json',
		data:'booknum='+$('#booked option:selected').val(),
		success:function(data){
			let jo = data[0]
			$('#_booknum').val($('#booked option:selected').val())
			$('#_roomname').val(jo['roomname'])
			$('#_roomtype').val(jo['roomtype'])
			$('#_person').val(jo['person'])
			$('#_begindate').val(jo['begindate'])
			$('#_enddate').val(jo['enddate'])
			$('#name').val(jo['name'])
			$('#mobile').val(jo['mobile'])
			$('#total').val(jo['total'])
			$('#_person').attr("placeholder",jo['availperson']+"�� ����")
			$('#optype').val('update')
			$('#complete').val('�������')
		} 
	})
})
function loadTypeData(){
	$.ajax({
		type:'get',url:'typelist',dataType:'json',
		success:function(data){
			$('#roomtype').empty();
			$('#roomtype').append('<option value=1 hidden selected></option>')
			for(i=0;i<data.length;i++){
				let jo = data[i];
				let str = '<option value='+jo['typenum']+'>'+jo['roomtype']+'</option>'
				$('#roomtype').append(str)
			}
			$('#roomtype').val(1).prop("selected",true)
			$('#optype').val('add')
			$('#complete').val('�����߰�')
		}
	})
	/* $.ajax({
		type:'get',url:'availlist', dataType:'json',
		success:function(data){
			$('#availroom').empty();
			for(i=0; i<data.length; i++){
				let jo = data[i]
				let str = "<option>"+jo['roomname']+", "
					+jo['roomtype']+", "+jo['person']+"��"+", "+jo['price']+"��</option>"
				$('#availroom').append(str)
			}
		}
	}) */
}
/* function loadBookedData(){
	$.ajax({
		type:'get',url:'booklist',dataType:'json',
		success:function(data){
			$('#booked').empty();
			for(i=0; i<data.length; i++){
				let jo = data[i]
				let str = "<option value="+jo['booknum']+">"+jo['roomname']+", "
					+jo['begindate']+"~"+jo['enddate']+", "+jo['name']+"</option>"
				$('#booked').append(str)
			}
		}
	})
} */
</script>
</body>
</html>