// Bar에 mouse hover시 내려올 div에 대한 함수

// downlist를 보이게하는 함수 mouse가 메뉴로 향하면 실행한다
function showDownList(n){
  var downLists = document.getElementsByClassName('extraBar');

  close = false;
  // 전부다 안보이게 한후
  for(var i = 0; i < downLists.length; i++)
    downLists[i].style.display = "none";
  if(n === 5)
    return;
  //하나만 block
  downLists[n].style.display = "block";
}

// downlist를 감추는 함수 mouse가 메뉴를 벗어나면 실행한다
function removeDownList(){
  var downLists = document.getElementsByClassName('extraBar');

    for(var i = 0; i < downLists.length; i++)
      downLists[i].style.display = "none";

}
