// 마커를 클릭시에 나타나는 정보 중, 사진 표현에 대한 함수

// 배열로 선언했지만, 후에 텍스트파일을 읽는 형태로 변경가능
var text = {'hot0':'서울의 랜드마크이자 대표적인 관광 명소로, 서울특별시 용산구 남산공원길에 있는 N서울타워와 서울타워플라자를 통칭한다. 해발고도 479.7m의 전망타워와 문화·상업 복합시설로 구성되어 있다.','hot1':"명동성당은 천주교 서울대교구의 주교좌 성당으로 한국 최초의 본당이자 한국 천주교를 대변하는 대성당이다. 이곳은 성당이 지닌 종교적·건축적 가치와 함께 우리 현대사가 요동치던 고비마다 지성과 양심의 보루로서 사회적 책무를 다해온 시대사적 의미 또한 높은 곳이다.",'hot2':'서울시 마포구 상암동 482번지 일대에 소재. 난지도 쓰레기매립장에 조성된 생태환경공원으로 2002년 5월 개원하였다. 공원은 억새식재지, 풍력발전기, 암석원, 혼생초지, 전망대, 전망휴게소, 순초지 등으로 조성되어 있다.','hot3':'인물과 공간에 대한 애정과 호기심이 가득 담긴 사진 및 감각적인 일러스트레이션을 통해 대중과 소통하는 토드 셀비는, 패션, 디자인, 영화, 건축, 요리 등 분야를 막론하고 트렌드 세터라면 꼭 함께 작업하기를 열망하는 아티스트로 주목받고 있습니다. 특히 작품에 담긴 셀비 특유의 유쾌함과 자유분방함은 단조로운 일상에 예술적 영감을 제공하고 긍정적인 에너지를 불러일으켜 왔습니다.','hot4':'오페라 하우스, 음악당, 미술관, 서예관, 예술자료관, 야외극장을 갖추고 있는 예술의전당 은 한국 최고의 복합문화예술공간이다. 다양한 장르의 문화예술행사가 동시에 열릴 수 있 는 세계적인 시설을 갖추고 있으며, 각 장르별 특성을 살려 지은 전용공간은 완벽한 예술 체험이 가능하다. 총 5개의 공연장이 마련된 오페라하우스와 음악당은 매년 800여회의 공 연이 열리고 있으며, 7개의 크고 작은 전시실을 갖춘 미술관과 서예작품 전용 전시장인 서 예관에서는 알찬 기획의 전시들이 연일 이어지고 있다.','hot5':'한강공원이 조성되기 이전부터 강변유원지로 유명했던 곳입니다. 수상에서는 시원한 바람을 맞으며 낭만과 젊음을 만끽할 수 있는 윈드서핑, 수상스키, 모터보트 등 수상스포츠가 활발하게 이루어지고 있습니다.','street0':'망원동이 경리단길처럼 유명해지면서 망원동과 경리단길을 합성한 신조어다.','street1':'강풀 만화거리','street2':'홍대 벽화거리','street3':'서울시 용산구 이태원2동에 위치한 지명으로, 국군재정관리단 정문에서부터 남산 그랜드하얏트 호텔 방향으로 이어지는 길과 주변 골목길을 이른다. 2012년 국군재정관리단으로 통합된 육군중앙경리단이 있었던 곳에서 길 이름이 유래됐다.','asset0':'경복궁과 창덕궁 사이에 위치한 한옥밀집지역. 종로구 삼청동ㆍ팔판동ㆍ사간동ㆍ가회동ㆍ계동ㆍ재동ㆍ안국동ㆍ송현동ㆍ소격동ㆍ원서동 일대에 기와집 형태의 한옥이 밀집ㆍ보존되고 있는 지역이다. 북촌(North Village)이라는 명칭은 청계천과 종로의 윗동네라는 의미에서 붙여진 것이다. 이 지역은 백악과 응봉을 연결하는 산줄기를 따라 북쪽에 비해 남쪽이 완만한 지형을 이뤄 배수가 잘돼 풍수지리적으로 좋은 지역으로 조선시대 사대부는 물론, 권문세가와 왕족들이 모여 살던 곳이다.','asset1':'서울특별시 종로구 세종로에 있는 경복궁의 배치도. 사적 제117호. 도성의 북쪽 북악산의 기슭에 위치한 경복궁은 남면하여 전체적으로 장방형으로 되어 있는데, 앞 부분에는 정전과 편전들이 놓이고 뒷부분에 침전과 후원이 자리잡은 전조후침의 형식을 갖추고 있다.','asset2':'서울시 중구 정동(貞洞)에 있는 조선 시대의 궁궐로 원래의 명칭은 경운궁(慶運宮)이지만, 1907년 고종이 순종에게 양위한 뒤 이곳에 살면서 명칭을 덕수궁(德壽宮)으로 바꾸었다.','asset3':'1617년(광해군 9) 인경궁(仁慶宮)·자수궁(慈壽宮)과 함께 건축을 시작하여 1623년 (광해군 15)에 완공하였다. 처음에는 경덕궁(慶德宮)이라 하였으나, 1760년(영조 36) 경희궁으로 고쳤다. 경희궁 자리는 원래 인조(仁祖)의 생부인 정원군(定遠君:뒤에 元宗으로 추존)의 잠저(潛邸)였는데, 여기에 왕기(王氣)가 서렸다고 하여 광해군(光海君)이 빼앗아 궁궐을 지었다.','park0':'실내 퍼레이드 코스를 따라 하루 2~3차례 퍼레이드 쇼가 펼쳐진다. 그 중에서도 월드카니발 퍼레이드는 동서양의 축제를 엄선하여 한곳에 모아 다양한 춤과 노래 그리고 볼거리를 펼치는 환상의 퍼레이드 퍼포먼스이다. 소요시간 30분.1989년 7월에 개원한 세계최대의 실내 테마파크로 1995년 기네스북에 등재되었다. 롯데월드는 테마파크 외에도 백화점·호텔·면세점·대형할인점·스포츠센터가 한 곳에 모여 있는 대단위 복합생활공간으로 관광에서 레저·쇼핑·문화·스포츠에 이르기까지 모든 것을 한곳에서 즐길 수 있다.','park1':'서울대공원은 세계 각국의 야생동물들이 살아 숨 쉬는 서울동물원과 다양한 재미와 즐거움을 주는 서울랜드, 자연과 사람의 특별한 이야기가 있는 테마가든 그리고 자연의 소중함을 배우는 서울대공원 캠핑장, 신나는 어린이 체험놀이터 기린나라, 근·현대 미술계의 흐름을 한눈에 볼 수 있는 국립현대미술관으로 구성되어 있으며 많은 시민들의 추억과 사랑을 한 몸에 받고 있는 대한민국 대표 종합 테마파크입니다.','park2':'어린이교통공원은 교통시설 및 유희 시설, 휴식 시설 등을 갖추어 자라나는 어린이들에게 즐겁게 뛰어놀면서 자연스럽게 교통 질서와 교통 도덕을 익히도록 각종 시설(신호등, 교통표지판, 횡단보도, 육교, 지하도, 터널, 고가차도)등을 마련해 놓은 곳이다. 이곳은 현재 유치원생을 비롯해 초등학생들이 많이 찾고 있으며 산 교육의 장소로 활용되고 있다. 또한, 이 공원에서는 서울지방경찰청에서 마련한 어린이 교통 시설로 경찰관의 도움을 받아 어린이들이 직접 자전거를 타고 다양한 도로를 돌아다니면서 실습을 통해 교통 학습을 받을 수 있다.'};


// 매개변수에 따른 모달창 띄워 줌
function showModal(title) {
  var modal = document.getElementById('myModal');
  var modalImg = document.getElementById("modalImg");
  var modalText = document.getElementById('modalText');

  modal.style.display = "block";
  modalImg.src = title+".PNG";
  modalText.innerHTML = text[title];
}
// 모달창이 동작할 때 생성되며 모달창 누르면 모달창 제거
window.onclick = function(event) {
  var modal = document.getElementById('myModal');
  var modalText = document.getElementsByClassName('modal-text')[0];

  if (event.target == modal || event.target == modalText) {
    modal.style.display = "none";
  }
}