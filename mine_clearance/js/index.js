function Mine(tr,td,mineNum){
	this.tr=tr;//行数
	this.td=td;//列数
	this.mineNum=mineNum;//雷的数量
	
	this.squares=[];//存储所有方块信息，他是一个二维数组，按行列顺序排放
	this.tds=[];//存储所有单元格的DOM(二维数组)
	this.surplusMine=mineNum;//剩余雷的数量
	this.allRight=false;//右击标的小红旗是否全都是雷，用来判断用户是否成功
	
	this.parent = document.querySelector(".gameBox");
	
}
//生成随机数
Mine.prototype.randomNum=function(){
	var square = new Array(this.tr*this.td);//生成一个空数组，数组长度为单元格个数
	for(var i =0;i<square.length;i++){
		square[i]=Math.round(Math.random()*(square.length));
	}
/*	square.sort(function(){return 0.5-Math.random()});*/

	return square.slice(1,this.mineNum);
};
//根据随机数存储数字或雷
Mine.prototype.init=function(){
	var rn=this.randomNum();//雷在格子里的位置
	var n=0;//用来找到格子对应的索引
	for(var i=0;i<this.tr;i++){
		this.squares[i]=[];
		for(var j=0;j<this.td;j++){
			n++;
			/*this.squares[i][j]=;*/
			//取一个方块在数组里的数据要使用行列方式去取，找方块周围的方块是用坐标的方式，坐标与行列方式的x、y正相反
			
			if(rn.indexOf(n)!=-1){
				//如果这个条件成立，说明这个索引对应的是个雷
				this.squares[i][j]={type:'mine',x:j,y:i};
			}else{
				this.squares[i][j]={type:'number',x:j,y:i,value:0};
			}
			
		}
	}
	//取消右击的默认样式
	this.parent.oncontextmenu=function(){
			return false;
	}		
//	console.log(this.squares);
	this.updateNum();
	this.createDom();
	//剩余雷数
	this.mineNum=document.querySelector(".mineNum");
	this.mineNum.innerHTML=this.surplusMine;
};

//创建表格
Mine.prototype.createDom=function(){
	var This=this;
	var table=document.createElement("table");
	for(var i=0;i<this.tr;i++){//行
		var domTr=document.createElement("tr");
		this.tds[i]=[];
		for(var j=0;j<this.td;j++){//列
			var domTd=document.createElement("td");
			
			domTd.pos=[i,j];//把格子的行与列存起来，以便通过这个值去数组中去对应的数据
			domTd.onmousedown=function(){
				This.play(event,this);//This代表的是实例对象，this代表的是实例对象
			};
			//domTd.innerText=1;
			this.tds[i][j]=domTd;
			
			/*if(this.squares[i][j].type=='mine'){
				domTd.className="mine";
			}
			if(this.squares[i][j].type=='number'){
				domTd.innerHTML=this.squares[i][j].value;
			}*/
			
			domTr.appendChild(domTd);
			
			}
		table.appendChild(domTr);
	}
	this.parent.innerHTML="";//避免多次创建
	this.parent.appendChild(table);
};
//寻找一个方格周围的八个方格
Mine.prototype.getAround=function(square){
	var x=square.x;
	var y=square.y;
	var result=[];//把找到的格子的坐标返回
	/*x-1,y-1         x,y-1         x+1,y-1
	 *x-1,y           x,y           x+1,y
	 *x-1,y+1         x,y+1         x+1,y+1
	 * 
	 * */
	//通过坐标去循环九宫格
	for(var i=x-1;i<=x+1;i++){
		for(var j=y-1;j<=y+1;j++){
			if( i<0 || //格子超出了左边的范围
				j<0 || //格子超出了上边的范围
				i>this.td-1 || //格子超出了右边的范围
				j>this.tr-1 || //格子超出了下边的范围
				(i==x&&j==y) || //当前格子是自己
				this.squares[j][i].type=='mine'){//周围格子是个雷
				continue;
			}
				result.push([j,i]);//以行列的方式返回出去
		}
	}
	return result;
}

//更新所有的数字
Mine.prototype.updateNum=function(){
	for(var i=0;i<this.tr;i++){
		for(var j=0;j<this.td;j++){
			//要更新的是雷周围的数字
			if(this.squares[i][j].type=='number'){
				continue;
			}
			//获取到每个雷周围的数字
			var num=this.getAround(this.squares[i][j]);
			for(var k=0;k<num.length;k++){
			/*	num[i]==[0][1];*/
				this.squares[num[k][0]][num[k][1]].value +=1;
			}
		}
	}
	console.log(this.squares);
};

//玩游戏
Mine.prototype.play=function(event,obj){
	var This = this;
	if(event.which==1 && obj.className!='flag'){//后面的条件是，表完小红旗后不能左键点击
		//点击的是左键
		var curSquare=this.squares[obj.pos[0]][obj.pos[1]];
		var cl=['zero','one','two','three','four','five','six','seven','eight'];
		
		
		/*console.log(curSquare);*/
		if(curSquare.type=='number'){
			//用户点到数字//点到非0
			obj.innerHTML=curSquare.value;
			obj.className=cl[curSquare.value];
			//点到0
			if(curSquare.value==0){
				obj.innerHTML='';//数字为0不显示
				//以找到的0周围8个格子找0， 再根据周围0再找周围0,如果没有则不需要再找
				function getAllZero(square){
					var around=This.getAround(square);
					for(var i =0;i<around.length;i++){
						var x=around[i][0];//行
						var y=around[i][1];//列
						
						This.tds[x][y].className=cl[curSquare.value];
						//如果点击某个格子为0 ，则递归继续寻找
						if(This.squares[x][y].value==0){
							//递归为0的位置继续寻找
							if(!This.tds[x][y].check){
								//给对应的td添加一个属性，这条属性用于记录此格子是否被找到过
								//如果找到过则为ture，下一次不会寻找
								This.tds[x][y].check=true;
								getAllZero(This.squares[x][y]);
							}
							
						}else{
							//如果周围不是0，则把数字显示出来
							This.tds[x][y].innerHTML=This.squares[x][y].value;
							This.tds[x][y].className=cl[This.squares[x][y].value]
						}
					}
					
					
				};
				getAllZero(curSquare);
			}

		}else{
			//用户点到雷
			
			this.gameOver(obj);
		}
	}
	
	//点击的是右键
	if(event.which==3){
		//如果右击的是数字，则点击无效
		if(obj.className && obj.className!='flag'){
			return;
		}
		obj.className=obj.className=='flag'?'':'flag';//切换class
		
		if(this.squares[obj.pos[0]][obj.pos[1]].type=='mine'){
			this.allRight==true;
		}else{
		this.allRight==false;
		}
		
		//剩余雷数量的数字变化
		if(obj.className=='flag'){
			this.mineNum.innerHTML=--this.surplusMine;
		}else{
			this.mineNum.innerHTML=++this.surplusMine;
		}
		
		//剩余雷数量为0时，表示用户已表完小红旗，要判断游戏成功还是结束
		if(this.surplusMine==0){
			if(this.allRight){
				//表示游戏成功
				alert("游戏成功");	
			}else{
				alert("游戏失败");
				this.gameOveer();
			}
		}
	}
	
};
//游戏失败
Mine.prototype.gameOver=function(clickTd){
	//1显示所有雷
	//2取消所有格子响应事件
	//3给点击雷设置特殊样式
	for(var i= 0 ;i<this.tr;i++){
		for(var j= 0;j<this.td;j++){
			if(this.squares[i][j].type=='mine'){
				this.tds[i][j].className="mine";
			}
			this.tds[i][j].onmousedown=null;
		}
	}
	if(clickTd){
		clickTd.style.backgroundColor='yellow';
	}
}
//button功能
var btns=document.getElementsByTagName("button");
var mine=null;
var arr=[[9,9,10],[16,16,40],[28,28,99]];
for(let i=0;i<btns.length-1;i++){
	btns[i].onclick=function(){
		mine = new Mine(...arr[i]);//es6
		mine.init();
	}
}
btns[0].onclick();//默认初级

//设置重新开始按钮
btns[3].onclick=function(){
	mine.init();
}

