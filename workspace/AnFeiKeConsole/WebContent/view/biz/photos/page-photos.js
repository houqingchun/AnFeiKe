$package('jeecg.photos');
jeecg.photos = function(){
	var _box = null;
	var _this = {
		config:{
			event:{
				add:function(){
					$('#typeIds_combobox').combobox('reload');
					_box.handler.add();
				},
				edit:function(){
					$('#typeIds_combobox').combobox('reload');
					_box.handler.edit();
				}
			},
  			dataGrid:{
  				title:'照片',
	   			url:'dataList.do',
	   			columns:[[
					{field:'id',checkbox:true},
					{field:'photoPath',title:'照片',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.photoPath;
							}
						},
					{field:'description',title:'介绍',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.description;
							}
						},
					{field:'mainId',title:'主表ID',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.mainId;
							}
						},
					{field:'mainType',title:'主表类别',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.mainType;
							}
						},
					{field:'createBy',title:'创建人',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.createBy;
							}
						},
					{field:'createTime',title:'创建时间',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.createTime;
							}
						},
					{field:'updateBy',title:'更新人',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.updateBy;
							}
						},
					{field:'updateTime',title:'修改时间',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.updateTime;
							}
						},
					]]
			}
		},
		init:function(){
			_box = new YDataGrid(_this.config); 
			_box.init();
		}
	}
	return _this;
}();

$(function(){
	jeecg.photos.init();
});