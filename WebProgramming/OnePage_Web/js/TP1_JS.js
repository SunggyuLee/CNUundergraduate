// localStorage.clear();
// 저장소 초기화

// dropdown 버튼이 눌렸는지를 판단 하는 변수
var isButtonClicked = false;

// mouse over 시 메뉴 바 내려옴 (css 효과)
function downDropDown() {
  var dropdown = document.getElementsByClassName('dropdown')[0];
  dropdown.style.display = "block";
}

// 버튼이 눌렸으면 메뉴 바 고정, 아니면 mouse out 시 사라진다
function deleteDropDown() {
  if(isButtonClicked === true)
    return;
  var dropdown = document.getElementsByClassName('dropdown')[0];
  dropdown.style.display = "none";
}

// x누르면 메뉴 바 제거, +누르면 메뉴 바 고정
function fixedDropDown(){
  var rotate = document.getElementById('plus');
  if(isButtonClicked === true){
    rotate.style.transform = "rotate(0)";
    rotate.style.transition = "1s";
    var dropdown = document.getElementsByClassName('dropdown')[0];
    dropdown.style.display = "none";
    isButtonClicked = false;
    return;
  }
  rotate.style.transform = "rotate(45deg)";
  rotate.style.transition = "1s";
  isButtonClicked = true;
}

// bool값(웹페이지가 처음 실행될 때 인지 아닌지)을 받아서 처음 실행되는면 이미지를 지워주기만하고
// 직접 이미지를 지우는 경우는 저장소에 저장
function removeImage(bool, n) {
  var tempDIV = document.getElementsByClassName('xButton')[n].parentNode;
  //부모의 처음 노드는 텍스트노드로 존재한다 눈에 안보임
  if(!bool){
    var tempText = tempDIV.childNodes[1].innerHTML;
    var numOfStorage = Number(localStorage.getItem("0"));
    localStorage.setItem("0",numOfStorage+1);
    localStorage.setItem(localStorage.getItem("0"),tempText);
  }
  tempDIV.style.display="none";
  tempDIV.parentNode.removeChild(tempDIV);
  var imgs = document.getElementsByClassName('gallery-images');
  for(var i = 0; i < imgs.length; i++){
    imgs[i].parentNode.getElementsByTagName('img')[0].setAttribute("onclick", "removeImage(false, "+i+")");
    imgs[i].parentNode.getElementsByTagName('img')[1].setAttribute("onclick", "modalWindow("+i+")");
    imgs[i].parentNode.getElementsByTagName('img')[1].setAttribute("onmouseover", "setText("+i+")");
    imgs[i].parentNode.getElementsByTagName('img')[1].setAttribute("onmouseout", "deleteText("+i+")");
  }
}
//갤러리 이미지 mouse over시 발생
function setText(n) {
  var text = document.getElementsByClassName('hoverText')[n];
  text.style.opacity = "1";
}
// 갤러리 이미지 mouse out시 발생
function deleteText(n) {
  var text = document.getElementsByClassName('hoverText')[n];
  text.style.opacity = "0";
}

// 저장소 확인해서 이미지 사전 제거
window.addEventListener('load', function(){
  //0번째 key는 아이템의 개수 값을 가진다
  if(localStorage.getItem("0") === null)
    localStorage.setItem("0","0");
  var imgs = document.getElementsByClassName('hoverText');
  var i = 1;
  while(localStorage.getItem(String(i)) !== null){
    for(var j = 0; j < imgs.length; j++){
      if(localStorage.getItem(String(i)) === imgs[j].innerHTML){
        removeImage(true, j);
        imgs = document.getElementsByClassName('hoverText');
      }
    }
    i++;
  }

});


var tempImgNumber;
// 매개변수에 따른 모달창 띄워 줌
function modalWindow(n) {
  var modal = document.getElementById('myModal');
  var modalImg = document.getElementById("modalImg");
  var tempImg = document.getElementsByClassName('gallery-images')[n];

  modal.style.display = "block";
  modal.getElementsByClassName('prev')[0].style.zIndex = "3";
  modal.getElementsByClassName('next')[0].style.zIndex = "3";
  modalImg.src = tempImg.src;

  //버튼으로 하는줄 알고 구현했었던 것
  // var xButton = document.getElementById('modalXButton');
  // xButton.onclick = function() {
  //   modal.style.display = "none";
  // }

  tempImgNumber = n;
}
// 모달창이 동작할 때 생성되며 모달창 누르면 모달창 제거
window.onclick = function(event) {
  var modal = document.getElementById('myModal');

  if (event.target == modal) {
    modal.style.display = "none";
  }
}

//tempImgNumber에 현재 모달 이미지를 저장해두었고 +-1하며 이미지 소스변경
function showModalImage(n){
  var modalImg = document.getElementById("modalImg");
  var imgs = document.getElementsByClassName('gallery-images');
  tempImgNumber += n;
  if(tempImgNumber > imgs.length-1)
    tempImgNumber = 0;
  else if(tempImgNumber < 0)
    tempImgNumber = imgs.length-1;

  modalImg.src = imgs[tempImgNumber].src;

}
var tempOffset = 0;
var isScrolled = false;
var offsetTop;
// 상단 메뉴를 누르면 offset을 받아 좌측상단에 해당 지점이 위치하도록 이동
// 이 때, setTimeout을 이용해 0.005초 마다 offset 25씩 이동 (한번에 옮기지않고 서서히 옮겨줌)
function movescroll(className) {
  //document.getElementsByClassName(className)[0].scrollIntoView();
  if(!isScrolled){
    offsetTop = document.getElementsByClassName(className)[0].offsetTop;
    isScrolled = true;
  }
  if(tempOffset > offsetTop){
    tempOffset = 0;
    isScrolled = false;
    return;
  }else{
    setTimeout(movescroll, 5);
  }
  window.scrollTo(0, tempOffset);
  tempOffset += 25;

}

var slideIndex = 0;
var myInterval;
// 1 or -1을 받아서 showSlide로 이미지 제공
function plusSlides(n) {
  showSlides(slideIndex += n);
}

// index를 받아 이미지 제공
function currentSlide(n) {
  showSlides(slideIndex = n);
}

// 버튼을 직접 눌렀을 때 발생,
// clearInterval로 자동으로 진행되던 함수를 멈추고 이미지를 띄워준 후 다시 자동으로 진행되게 해준다
// mutual exclusion 제공하기 위해
function showSlides(n) {
  clearInterval(myInterval);
  var slides = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("dot");
  if (n > slides.length-1) {slideIndex = 0}
  if (n < 0) {slideIndex = slides.length-1}
  for (var i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";
  }
  for (i = 0; i < dots.length; i++) {
      dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex].style.display = "block";
  dots[slideIndex].className += " active";
  myInterval = setInterval(showTimeSlides, 5000);
}

// 5초마다 진행되는 함수
function showTimeSlides() {
  slideIndex++;
  var slides = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("dot");
  if (slideIndex > slides.length-1) {slideIndex = 0}
  if (slideIndex < 0) {slideIndex = slides.length-1}
  for (var i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";
  }
  for (i = 0; i < dots.length; i++) {
      dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex].style.display = "block";
  dots[slideIndex].className += " active";
}

// 새로 등록되는 테이블의 id에 사용
var boardIndex = 0;
// 이미 display:none으로 만들어논 테이블 샘플을 이용해 받아온 정보를 넣어서 보여줌
function createTable() {
  var writer = document.getElementsByName('writer')[0].value;
  var contents = document.getElementsByName('content')[0].value;
  document.getElementsByName('writer')[0].value = "";
  document.getElementsByName('content')[0].value = "";
  if((writer || contents) === ""){
    alert("입력해주세요"); return;
  }
  //방명록 위에 답글이 나오는 것이 아니라 사이에 들어가야된다는 것을 알고 급히 수정한 부분
  //nextSibling이 두 번인 이유는 각 태그마다 textNode를 Sibling으로 가진다
  var sampleTable = document.getElementById('board').getElementsByTagName('thead')[0].nextSibling.nextSibling;
  console.log(sampleTable);
  var cloneTable = sampleTable.cloneNode(true);
  cloneTable.style.display = "table-row-group";
  cloneTable.id = "board_contents" + boardIndex;
  cloneTable.style.background = "rgb(255, 177, 0)";
  cloneTable.getElementsByTagName('td')[0].innerHTML = "<strong>Writer : </strong>" + writer;
  cloneTable.getElementsByTagName('td')[1].innerHTML = contents;
  cloneTable.getElementsByTagName('button')[0].setAttribute("onclick", "commentCreate("+ boardIndex +")");
  var parent = document.getElementById('board');
  var child = parent.childNodes[boardIndex++];
  parent.insertBefore(cloneTable, child);
  //appendChild는 맨밑에 추가
}

// 열합성되어있는 tr하나를 만들면 되기 때문에 createElement를 이용해 직접 만들고 꾸며서 위에서 만들어진 테이블에 연결
function commentCreate(n){

  var comment = prompt("답글을 입력하세요");
  var tempTR = document.createElement('tr');
  var tempTD = document.createElement('td');
  tempTD.style.backgroundImage = "-webkit-linear-gradient(right, rgb(255, 230, 143), rgb(250, 150, 0))";
  tempTD.setAttribute("colspan", "2");
  var tempComment = document.createTextNode("reply : " +comment);
  if(comment === null || comment === ""){
    alert("답글을 입력하세요")
    return;
  }
  tempTD.appendChild(tempComment);
  tempTR.appendChild(tempTD);
  var parent = document.getElementById('board_contents'+n);
  var child = document.getElementsByClassName('reply')[n];
  parent.insertBefore(tempTR, child);
}
