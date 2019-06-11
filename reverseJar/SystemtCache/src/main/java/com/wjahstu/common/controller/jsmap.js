var MapClass={
    map : new Array(),
    //Update or Insert
    setAt : function(key, value){      
      for (var i = 0; i < this.map.length; i++){
        if ( this.map[i].key === key ){
          this.map[i].value = value;
          return;
        }
      }
      this.map[this.map.length] = new struct(key, value);    
    },
    //Query
    lookUp : function(key){
      for (var i = 0; i < this.map.length; i++){
        if ( this.map[i].key === key ){
          return this.map[i].value;
        }
      }      
      return null;
    },
    //Delete
    removeKey : function(key){
		var v;
		for (var i = 0; i < this.map.length; i++){
			v = this.map.pop();
			if ( v.key === key ) continue;          
			this.map.unshift(v);
		}
    }, 
    //删除所有
	removeAll : function(){
		this.map = new Array();
	}, 
    getCount: function(){
      return this.map.length;
    },    
    isEmpty : function(){
      return this.map.length <= 0;
    }
};
function struct(key, value){
  this.key = key;
  this.value = value;
};