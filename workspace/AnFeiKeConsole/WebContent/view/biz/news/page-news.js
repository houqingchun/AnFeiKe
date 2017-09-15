$package('jeecg.news');
jeecg.news = function(){
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
  				title:'新闻',
	   			url:'dataList.do',
	   			columns:[[
					{field:'id',checkbox:true},
					{field:'subject',title:'标题',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.subject;
							}
						},
					{field:'photoPath',title:'照片',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.photoPath;
							}
						},
					{field:'status',title:'状态',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.status;
							}
						},
					{field:'types',title:'类别',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.types;
							}
						},
					{field:'onTop',title:'置顶',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.onTop;
							}
						},
					{field:'description',title:'详细描述',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.description;
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
	jeecg.news.init();
});