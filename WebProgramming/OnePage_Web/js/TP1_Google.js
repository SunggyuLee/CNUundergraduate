// 공대 5호관 위치 좌표
var myCenter=new google.maps.LatLng(36.366516,127.344302);

 function initialize()
 {
		var mapProp = {
			center:myCenter,
			zoom:17,
			mapTypeId:google.maps.MapTypeId.ROADMAP
		};
    // mapProp의 설정에 따라 googleMap에 맵을 그려준다
		var map=new google.maps.Map(document.getElementById("googleMap"),mapProp);
    // 맵에 표시되는 마커와 설명
		var marker=new google.maps.Marker({
			position:myCenter,
		});

		marker.setMap(map);

		var infowindow = new google.maps.InfoWindow({
			content:"Sesame's House"
		});

		infowindow.open(map,marker);
 }
 // 웹페이지 실행 시, 함수 실행
 google.maps.event.addDomListener(window, "load", initialize);
