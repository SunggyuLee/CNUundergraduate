var map; // 계속해서 사용할 map

// 이미지 배열
var parkImage = ['park0.PNG','park1.PNG','park2.PNG'];
var hotImage =['hot0.PNG','hot1.PNG','hot2.PNG','hot3.PNG','hot4.PNG','hot5.PNG'];
var streetImage = ['street0.PNG','street1.PNG','street2.PNG','street3.PNG'];
var assetImage = ['asset0.PNG','asset1.PNG','asset2.PNG','asset3.PNG'];
//각각 분류에 따른 마커 배열
var hots = [];
var streets = [];
var assets = [];
var parks = [];
// 아이고에서 사용하는 변수
var like = 0;

// 각 관광지의 좌표와 사진
var hotPositions = [
  {
    //남산타워
    content: '<div style="width:200px; height:150px"><img style="width:100%" src='+hotImage[0]+'></div>',
    latlng: new google.maps.LatLng(37.551262, 126.988184)
  },
  {
    //명동성당
    content: '<div style="width:200px; height:150px"><img style="width:100%" src='+hotImage[1]+'></div>',
    latlng: new google.maps.LatLng(37.563364, 126.987382)
  },
  {
    //하늘공원
    content: '<div style="width:200px; height:150px"><img style="width:100%" src='+hotImage[2]+'></div>',
    latlng: new google.maps.LatLng(37.568299, 126.884891)
  },
  {
    //디뮤지엄
    content: '<div style="width:200px; height:150px"><img style="width:100%" src='+hotImage[3]+'></div>',
    latlng: new google.maps.LatLng(37.537202, 127.011437)
  },
  {
    //예술의 전당
    content: '<div style="width:200px; height:150px"><img style="width:100%" src='+hotImage[4]+'></div>',
    latlng: new google.maps.LatLng(37.478715, 127.011824)
  },
  {
    //뚝섬한강공원
    content: '<div style="width:200px; height:150px"><img style="width:100%" src='+hotImage[5]+'></div>',
    latlng: new google.maps.LatLng(37.529117, 127.071345)
  }
];

var streetPositions = [
  {
    //망리단길
    content: '<div style="width:200px; height:150px"><img style="width:100%" src='+streetImage[0]+'></div>',
    latlng: new google.maps.LatLng(37.555070, 126.905190)
  },
  {
    //강풀만화거리
    content: '<div style="width:200px; height:150px"><img style="width:100%" src='+streetImage[1]+'></div>',
    latlng: new google.maps.LatLng(37.535755, 127.130811)
  },
  {
    //홍대벽화거리
    content: '<div style="width:200px; height:150px"><img style="width:100%" src='+streetImage[2]+'></div>',
    latlng: new google.maps.LatLng(37.551864, 126.924107)
  },
  {
    //경리단길
    content: '<div style="width:200px; height:150px"><img style="width:100%" src='+streetImage[3]+'></div>',
    latlng: new google.maps.LatLng(37.538630, 126.988070)
  }
];

var assetPositions = [
  {
    //한옥마을
    content: '<div style="width:200px; height:150px"><img style="width:100%" src='+assetImage[0]+'></div>',
    latlng: new google.maps.LatLng(37.579604, 126.982391)
  },
  {
    //경복궁
    content: '<div style="width:200px; height:150px"><img style="width:100%" src='+assetImage[1]+'></div>',
    latlng: new google.maps.LatLng(37.579864, 126.977052)
  },
  {
    //덕수궁
    content: '<div style="width:200px; height:150px"><img style="width:100%" src='+assetImage[2]+'></div>',
    latlng: new google.maps.LatLng(37.566077, 126.975092)
  },
  {
    //경희궁
    content: '<div style="width:200px; height:150px"><img style="width:100%" src='+assetImage[3]+'></div>',
    latlng: new google.maps.LatLng(37.571467, 126.968137)
  }
];

var parkPositions = [
  {
    //롯데월드
    content: '<div style="width:200px; height:150px"><img style="width:100%" src='+parkImage[0]+'></div>',
    latlng: new google.maps.LatLng(37.511371, 127.098103)
  },
  {
    //서울대공원
    content: '<div style="width:200px; height:150px"><img style="width:100%" src='+parkImage[1]+'></div>',
    latlng: new google.maps.LatLng(37.427780, 127.017025)
  },
  {
    //어린이교통공원
    content: '<div style="width:200px; height:150px"><img style="width:100%" src='+parkImage[2]+'></div>',
    latlng: new google.maps.LatLng(37.516195, 127.101902)
  }
];


// 구글 맵을 불러오는 부분
 function initialize()
 {
		var mapProp = {
			center: new google.maps.LatLng(37.551262, 126.988184),
			zoom:13,
			mapTypeId:google.maps.MapTypeId.ROADMAP
		};
    // mapProp의 설정에 따라 googleMap에 맵을 그려준다
		map=new google.maps.Map(document.getElementById("googleMap"),mapProp);


    // 마커를 생성하고 지도위에 표시하는 함수입니다
    for (var i = 0; i < hotPositions.length; i ++) {
        var imageSrc = "Img/hot.PNG";
        var imageSize = new google.maps.Size(30,50);
        var markerImage = new google.maps.MarkerImage(imageSrc, null, null, null, imageSize);
        // 마커를 생성합니다
        var marker = new google.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: hotPositions[i].latlng, // 마커를 표시할 위치
            icon: markerImage,
            title: "hot"+i
        });
        // 마커에 마우스 hover시 보여줄 content
        var infoWindow = new google.maps.InfoWindow({
          content:hotPositions[i].content
        });
        // click, over, out 이벤트 적용
        (function(marker, infoWindow) {
            // click시 모달창 띄워줌 (별다른 수고없이 JS가 서로 연동이 가능하다)
            google.maps.event.addListener(marker, 'click', function() {
              showModal(marker.getTitle());
            });
            // 마커에 mouseover 이벤트를 등록하고 마우스 오버 시 인포윈도우를 표시합니다
            google.maps.event.addListener(marker, 'mouseover', function() {
                infoWindow.open(map, marker);
            });

            // 마커에 mouseout 이벤트를 등록하고 마우스 아웃 시 인포윈도우를 닫습니다
            google.maps.event.addListener(marker, 'mouseout', function() {
                infoWindow.close();
            });
        })(marker, infoWindow);
        // 분류하여 사용하기위해 같은 분류 마커는 배열로 묶음
        hots.push(marker);
    }
    // 거리 관련 마커 생성
    for (var i = 0; i < streetPositions.length; i ++) {
        var imageSrc = "Img/street.PNG";
        var imageSize = new google.maps.Size(30,50);
        var markerImage = new google.maps.MarkerImage(imageSrc, null, null, null, imageSize);
        // 마커를 생성합니다
        var marker = new google.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: streetPositions[i].latlng, // 마커를 표시할 위치
            icon: markerImage,
            title: "street"+i
        });

        var infoWindow = new google.maps.InfoWindow({
          content:streetPositions[i].content
        });

        (function(marker, infoWindow) {
          // click시 모달창 띄워줌
          google.maps.event.addListener(marker, 'click', function() {
            showModal(marker.getTitle());
          });
            // 마커에 mouseover 이벤트를 등록하고 마우스 오버 시 인포윈도우를 표시합니다
            google.maps.event.addListener(marker, 'mouseover', function() {
                infoWindow.open(map, marker);
            });

            // 마커에 mouseout 이벤트를 등록하고 마우스 아웃 시 인포윈도우를 닫습니다
            google.maps.event.addListener(marker, 'mouseout', function() {
                infoWindow.close();
            });
        })(marker, infoWindow);

        streets.push(marker);
    }
    // 문화재 관련 마커 생성
    for (var i = 0; i < assetPositions.length; i ++) {
        var imageSrc = "Img/asset.PNG";
        var imageSize = new google.maps.Size(30,50);
        var markerImage = new google.maps.MarkerImage(imageSrc, null, null, null, imageSize);
        // 마커를 생성합니다
        var marker = new google.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: assetPositions[i].latlng, // 마커를 표시할 위치
            icon: markerImage,
            title: "asset"+i
        });

        var infoWindow = new google.maps.InfoWindow({
          content:assetPositions[i].content
        });

        (function(marker, infoWindow) {
          // click시 모달창 띄워줌
          google.maps.event.addListener(marker, 'click', function() {
            showModal(marker.getTitle());
          });
            // 마커에 mouseover 이벤트를 등록하고 마우스 오버 시 인포윈도우를 표시합니다
            google.maps.event.addListener(marker, 'mouseover', function() {
                infoWindow.open(map, marker);
            });

            // 마커에 mouseout 이벤트를 등록하고 마우스 아웃 시 인포윈도우를 닫습니다
            google.maps.event.addListener(marker, 'mouseout', function() {
                infoWindow.close();
            });
        })(marker, infoWindow);

        assets.push(marker);
    }
    // 놀이공원 관련 마커 생성
    for (var i = 0; i < parkPositions.length; i ++) {
        var imageSrc = "Img/park.PNG";
        var imageSize = new google.maps.Size(30,50);
        var markerImage = new google.maps.MarkerImage(imageSrc, null, null, null, imageSize);
        // 마커를 생성합니다
        var marker = new google.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: parkPositions[i].latlng, // 마커를 표시할 위치
            icon: markerImage,
            title:"park"+i
        });

        var infoWindow = new google.maps.InfoWindow({
          content:parkPositions[i].content,
        });

        (function(marker, infoWindow) {
          // click시 모달창 띄워줌
            google.maps.event.addListener(marker, 'click', function() {
              showModal(marker.getTitle());
            });
            // 마커에 mouseover 이벤트를 등록하고 마우스 오버 시 인포윈도우를 표시합니다
            google.maps.event.addListener(marker, 'mouseover', function() {
                infoWindow.open(map, marker);
            });

            // 마커에 mouseout 이벤트를 등록하고 마우스 아웃 시 인포윈도우를 닫습니다
            google.maps.event.addListener(marker, 'mouseout', function() {
                infoWindow.close();
            });
        })(marker, infoWindow);

        parks.push(marker);
    }
    // map자체를 클릭시에 placeMarker호출
    google.maps.event.addListener(map, 'click', function(event) {
       placeMarker(event.latLng);
    });
    // 좋아요 마커를 생성하는 함수
    function placeMarker(location) {
      var imageSrc = "Img/like.PNG";
      var imageSize = new google.maps.Size(30,50);
      var markerImage = new google.maps.MarkerImage(imageSrc, null, null, null, imageSize);
      // 내용을 받아 그 내용을 infoWindow로 제작
      var content = prompt('내용을 입력해주세요.');
      if(content === "" || content === null)
        return;

      var marker = new google.maps.Marker({
          position: location,
          map: map,
          icon:markerImage
      });

      var infoWindow = new google.maps.InfoWindow({
        content:content
      });

      var temp = like;
      localStorage.setItem(String(temp), marker.getPosition());

      // temp를 따로 둔 이유는 like로는 같은 것이라고 인식을 못함
      (function(marker, infoWindow) {
          // 마커 다시 클릭하면 없어짐 저장소에서도 제거
          google.maps.event.addListener(marker, 'click', function() {

          localStorage.removeItem(String(temp));
          marker.setMap(null);
        });
          // 마커에 mouseover 이벤트를 등록하고 마우스 오버 시 인포윈도우를 표시합니다
          google.maps.event.addListener(marker, 'mouseover', function() {
              infoWindow.open(map, marker);
          });

          // 마커에 mouseout 이벤트를 등록하고 마우스 아웃 시 인포윈도우를 닫습니다
          google.maps.event.addListener(marker, 'mouseout', function() {
              infoWindow.close();
          });
      })(marker, infoWindow);
      like++;
    }
 }
 // 웹페이지 실행 시, 함수 실행
 google.maps.event.addDomListener(window, "load", initialize);

//각 마커들 묶음 전체를 표시하는 함수
 function setHotMarkers(map) {
     for (var i = 0; i < hots.length; i++) {
         hots[i].setMap(map);
     }
 }

 function setStreetMarkers(map) {
     for (var i = 0; i < streets.length; i++) {
         streets[i].setMap(map);
     }
 }

 function setAssetMarkers(map) {
     for (var i = 0; i < assets.length; i++) {
         assets[i].setMap(map);
     }
 }

 function setParkMarkers(map) {
     for (var i = 0; i < parks.length; i++) {
         parks[i].setMap(map);
     }
 }

 // 카테고리를 클릭했을 때 type에 따라 카테고리의 스타일과 지도에 표시되는 마커를 변경합니다
 function changeMarker(type){

     if (type === 'hot') {
         setHotMarkers(map);
         setStreetMarkers(null);
         setAssetMarkers(null);
         setParkMarkers(null);
     } else if (type === 'street') {
       setHotMarkers(null);
       setStreetMarkers(map);
       setAssetMarkers(null);
       setParkMarkers(null);
     } else if (type === 'asset') {
       setHotMarkers(null);
       setStreetMarkers(null);
       setAssetMarkers(map);
       setParkMarkers(null);
     } else if (type === 'park') {
       setHotMarkers(null);
       setStreetMarkers(null);
       setAssetMarkers(null);
       setParkMarkers(map);
     } else if (type === 'all'){
       setHotMarkers(map);
       setStreetMarkers(map);
       setAssetMarkers(map);
       setParkMarkers(map);
     }
}

// 하나의 마커에 대해 표시하고 그쪽으로 포커스를 옮기는 함수
function focusNewLocation(type, n){
var position;
  if (type === 'hot') {
    setHotMarkers(null);
    setStreetMarkers(null);
    setAssetMarkers(null);
    setParkMarkers(null);
    hots[n].setMap(map);
    position = hotPositions[n];
  } else if (type === 'street') {
    setHotMarkers(null);
    setStreetMarkers(null);
    setAssetMarkers(null);
    setParkMarkers(null);
    streets[n].setMap(map);
    position = streetPositions[n];
  } else if (type === 'asset') {
    setHotMarkers(null);
    setStreetMarkers(null);
    setAssetMarkers(null);
    setParkMarkers(null);
    assets[n].setMap(map);
    position = assetPositions[n];
  } else if (type === 'park') {
    setHotMarkers(null);
    setStreetMarkers(null);
    setAssetMarkers(null);
    setParkMarkers(null);
    parks[n].setMap(map);
    position = parkPositions[n];
  }
  map.setCenter(position.latlng);
  map.setZoom(15);
}

// 저장소에 저장된 like 마커를 웹페이지 호출시 화면에 뿌려주는 함수
function showMyLike(){

  for(var key in localStorage){
    var imageSrc = "Img/like.PNG";
    var imageSize = new google.maps.Size(30,50);
    var markerImage = new google.maps.MarkerImage(imageSrc, null, null, null, imageSize);
    var location = localStorage.getItem(key);

    if(location === null)
      continue;
    var latlng = location.split('(')[1].split(')')[0];

    var marker = new google.maps.Marker({
        map: map,
        position: new google.maps.LatLng(latlng),
        icon:markerImage
    });
    // 위 부분을 지나면 Latlng을 받아오지 못해 해결 중
    marker.setMap(map);
  }
}
