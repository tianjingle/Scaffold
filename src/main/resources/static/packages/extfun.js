var msgTip;
/**
 * extjs 辅助方法
 */
var pri_action = function(url, json, fun) {
	Ext.Ajax.request({
				type : "POST",
				url : url,
				params : json,
				headers : {
					'Content-Type' : 'application/json; charset=utf-8'
				},
				success : function(response) {
					var text = JSON.parse(response.responseText);
					if (!text.success) {
						Ext.Msg.alert('提示', text.msg);
					} else {
						fun();
					}
				}
			})
}
/**
 * 公用的增、删、改 方法
 * 
 * @type
 */
var sysExt = {
	/**
	 * 修改方法
	 * 
	 * @param {}
	 *            url
	 * @param {}
	 *            panel
	 */
	upd : function(url, panel) {
		var json = this.getSelectByJson(panel);
		if (json == null) {
			Ext.Msg.alert('提示', '请勾选条目。');
		} else {
			Ext.Msg.confirm('信息确认', '您确认修改吗？', function(btn) {
						if (btn == 'yes') {
							var fun = function() {
								panel.getSelectionModel().deselectAll();
								panel.getStore().load();
							}
							pri_action(url, json, fun);
						}
					});
		}
	},
	updFun : function(url, panel, fun) {
		var json = this.getSelectByJson(panel);
		if (json == null) {
			Ext.Msg.alert('提示', '请勾选条目。');
		} else {
			Ext.Msg.confirm('信息确认', '您确认修改吗？', function(btn) {
						if (btn == 'yes') {
							pri_action(url, json, fun);
						}
					});
		}
	},
	updFunByID : function(url, panel) {
		var json = this.getSelectByJsonID(panel);
		if (json == null) {
			Ext.Msg.alert('提示', '请勾选条目。');
		} else {
			Ext.Msg.confirm('信息确认', '您确认修改吗？', function(btn) {
						var fun = function() {
							panel.setLoading(false);
							panel.getSelectionModel().deselectAll();
							panel.getStore().load();
						}
						if (btn == 'yes') {
							pri_action(url, json, fun);
						}
					});
		}
	},
	updFunByID : function(url, panel, fun) {
		var json = this.getSelectByJsonID(panel);
		if (json == null) {
			Ext.Msg.alert('提示', '请勾选条目。');
		} else {
			Ext.Msg.confirm('信息确认', '您确认修改吗？', function(btn) {
				if (btn == 'yes') {
					pri_action(url, json, fun);
				}
			});
		}
	},
	updForm : function(url, form, panel) {
		var json = this.getSelectByJson(panel);
		if (json == null) {
			Ext.Msg.alert('提示', '请勾选条目。');
		} else {
			Ext.Msg.confirm('信息确认', '您确认修改吗？', function(btn) {
				if (btn == 'yes') {
					if (form.isValid()) {
						form.submit({
									url : url,
									waitTitle : '提示',
									waitMsg : '数据提交中...',
									success : function(form, action) {
										form.reset();
										panel.getSelectionModel().deselectAll();
										panel.getStore().load();
									},
									failure : function(form, action) {
										var text = JSON
												.parse(action.response.responseText);
										Ext.Msg.alert('消息', text.msg);
									}
								});
					}
				}
			});
		}
	},
	add : function(url, form, panel) {
		if (form.isValid()) {
			form.submit({
						url : url,
						waitTitle : '提示',
						waitMsg : '数据提交中...',
						success : function(form, action) {
							form.reset();
							panel.getStore().load();
						},
						failure : function(form, action) {
							var text = JSON.parse(action.response.responseText);
							Ext.Msg.alert('消息', text.msg);
						}
					});
		}
	},
	addFun : function(url, form, fun) {
		if (form.isValid()) {
			form.submit({
						url : url,
						waitTitle : '提示',
						waitMsg : '数据提交中...',
						success : function(form, action) {
							fun();
						},
						failure : function(form, action) {
							var text = JSON.parse(action.response.responseText);
							Ext.Msg.alert('消息', text.msg);
						}
					});
		}
	},
	del : function(url, panel) {
		var json = this.getSelectByJson(panel);
		if (json == null) {
			Ext.Msg.alert('提示', '请勾选条目。');
		} else {
			Ext.Msg.confirm('信息确认', '您确认删除吗？', function(btn) {
						if (btn == 'yes') {
							var fun = function() {
								panel.getStore().load();
							}
							pri_action(url, json, fun);
						}
					});
		}

	},
	delByID : function(url, panel) {
		var json = this.getSelectByJsonID(panel);
		if (json == null) {
			Ext.Msg.alert('提示', '请勾选条目。');
		} else {
			Ext.Msg.confirm('信息确认', '您确认删除吗？', function(btn) {
						if (btn == 'yes') {
							var fun = function() {
								panel.getStore().load();
								Ext.Msg.alert('提示', '删除成功');
							}
							pri_action(url, json, fun);
						}
					});
		}

	},
	delFun : function(url, panel, fun) {
		var json = this.getSelectByJson(panel);
		if (json == null) {
			Ext.Msg.alert('提示', '请勾选条目。');
		} else {
			Ext.Msg.confirm('信息确认', '您确认删除吗？', function(btn) {
						if (btn == 'yes') {
							pri_action(url, json, fun);
						}
					});
		}

	},
	getSelectByJson : function(panel) {
		var last = null;
		var records = this.getRecords(panel);
		if (records.length != 0) {
			var jsonList = [];
			for (var i = 0; i < records.length; i++) {
				jsonList.push(records[i].data);
			}
			last = Ext.JSON.encode(jsonList);
		}
		return last;
	},
	
	getSelectByJsonID : function(panel) {
		var last = null;
		var records = this.getRecords(panel);
		if (records.length != 0) {
			var jsonList = [];
			for (var i = 0; i < records.length; i++) {
				var obj = {};
				obj.id = records[i].data.id;
				jsonList.push(obj);
			}
			last = Ext.JSON.encode(jsonList);
		}
		return last;
	},
	
	getSinData : function(panel) {
		var records = sysExt.getRecords(panel);
		if (records.length == 0) {
			return null;
		}
		if (records.length > 1) {
			return null;
		} else {
			return sysExt.getRecords(panel)[0].data;
		}
	},
	ajaxUrl : function(url, parm) {
		var data;
		Ext.Ajax.request({
					type : "POST",
					url : url + '?id=' + parm,
					headers : {
						'Content-Type' : 'application/json; charset=utf-8'
					},
					async : false,
					callback : function(options, success, response) {
						var text = JSON.parse(response.responseText);
						data = text.data;
					}
				})
		return data;
	},
	ajaxUrlFun : function(url, parm, fun) {
		Ext.Ajax.request({
					type : "POST",
					url : url + '?id=' + parm,
					headers : {
						'Content-Type' : 'application/json; charset=utf-8'
					},
					async : false,
					callback : function(options, success, response) {
						var text = JSON.parse(response.responseText);
						if (text.success) {
							fun();
						} else {
							if (text.msg == '') {
								text.msg = '操作失败.';
							}
							sysExt.alert(text.msg)
						}
					}
				})
	},
	ajaxUrlData : function(url, parm) {
		var data;
		Ext.Ajax.request({
					type : "POST",
					url : url + '?id=' + parm,
					headers : {
						'Content-Type' : 'application/json; charset=utf-8'
					},
					async : false,
					callback : function(options, success, response) {
						var text = JSON.parse(response.responseText);
						alert(text.success);
						if (text.success) {
							data = text;
						}
					}
				})
		return data;
	},
	ajaxJsonFun : function(url, json, fun) {
		Ext.Ajax.request({
					type : "POST",
					url : url,
					params : json,
					headers : {
						'Content-Type' : 'application/json; charset=utf-8'
					},
					async : false,
					callback : function(options, success, response) {
						var text = JSON.parse(response.responseText);
						if (text.success) {
							fun();
						} else {
							if (text.msg == '') {
								text.msg = '操作失败.';
							}
							sysExt.alert(text.msg)
						}
					}
				})
	},
	subJson : function(url, json, fun) {
		Ext.Ajax.request({
					type : "POST",
					url : url,
					params : json,
					headers : {
						'Content-Type' : 'application/json; charset=utf-8'
					},
					success : function(response) {
						var text = JSON.parse(response.responseText);
						if (!text.success) {
							Ext.Msg.alert('消息', text.msg);
						} else {
							fun();
						}
					}
				})
	},
	subform : function(url, form, fun) {
		if (form.isValid()) {
			form.submit({
						url : url,
						waitTitle : '提示',
						waitMsg : '数据提交中...',
						success : function(form, action) {
							fun();
						},
						failure : function(form, action) {
							var text = JSON.parse(action.response.responseText);
							Ext.Msg.alert('消息', text.msg);
						}
					});
		}
	},
	alert : function(msg) {
		Ext.Msg.alert('提示', msg);
	},
	getWinWidth : function() {
		var winWidth = 0;
		if (window.innerWidth)
			winWidth = window.innerWidth;
		else if ((document.body) && (document.body.clientWidth))
			winWidth = document.body.clientWidth;
		if (document.documentElement && document.documentElement.clientHeight
				&& document.documentElement.clientWidth) {
			winWidth = document.documentElement.clientWidth;
		}
		return winWidth;
	},
	getWinHeight : function() {
		var winHeight = 0;
		if (window.innerHeight)
			winHeight = window.innerHeight;
		else if ((document.body) && (document.body.clientHeight))
			winHeight = document.body.clientHeight;
		if (document.documentElement && document.documentElement.clientHeight
				&& document.documentElement.clientWidth) {
			winHeight = document.documentElement.clientHeight;
		}
		return winHeight;
	},
	getTreeSelection : function(treePanel) {
		var selectionMode = treePanel.getSelectionModel();
		var selection = selectionMode.getSelection(); // 获取选中的值
		if (selection.length == 0) {
			return null;
		} else {
			return selection[0].data;
		}
	},
	getTreeNode : function(treePanel) {
		var selectionMode = treePanel.getSelectionModel();
		var selection = selectionMode.getSelection(); // 获取选中的值
		if (selection.length == 0) {
			return null;
		} else {
			return selection[0];
		}
	},
	getTreeText : function(treePanel) {
		if (this.getTreeSelection(treePanel) == null) {
			return null;
		}
		return this.getTreeSelection(treePanel).text;
	},
	getTreeId : function(treePanel) {
		if (this.getTreeSelection(treePanel) == null) {
			return null;
		}
		return this.getTreeSelection(treePanel).id;
	},
	getTreeLeaf : function(treePanel) {
		if (this.getTreeSelection(treePanel) == null) {
			return null;
		}
		return this.getTreeSelection(treePanel).leaf;
	},
	getTreeParent : function(treePanel) {
		var id = this.getTreeId(treePanel);
		if (id == null) {
			return null;
		}
		return treePanel.getStore().getNodeById(id).parentNode.getId();
	},
	changeNodeLeaf : function(node) {
		if (node.isRoot() || node.hasChildNodes()) {
			return false;
		}
		var nodeText = node.get('text');
		var isLeaf = node.isLeaf();
		var _id = node.data.id;
		var _isgroup;
		if(node.data.isgroup){
			_isgroup = node.data.isgroup
		}
		var parentNode = node.parentNode;
		// 取得点击节点的相邻下一个节点，以便删除之后插入时做标识，知道从哪里插入
		var nextSibling = node.nextSibling;
		// 删除节点
		node.remove();
		// 创建一个一样的节点
		if(node.data.isgroup){
			parentNode.insertBefore({
					id : _id,
					text : nodeText,
					isgroup:_isgroup,
					leaf : !isLeaf
				}, nextSibling);
		}else{
			parentNode.insertBefore({
					id : _id,
					text : nodeText,
					leaf : !isLeaf
				}, nextSibling);
		}
		
	},
	getRecords : function(panel) {
		return panel.getSelectionModel().getSelection();
	},
	getSelVal : function(panel, field) {
		var record = panel.getSelectionModel().getSelection();
		return record[0].get(field);
	},
	ecode : function(obj) {
		return Ext.encode(obj);
	},
	dcode : function(obj) {
		return Ext.dencode(obj);
	},
	getTime:function(obj){
		var date = new Date(obj);
		return date.getTime();
	},
	typeOf:function(obj){
		return Ext.typeOf(obj);
	},
	showLoad:function(msg){
		if(msg){
			msgTip = new Ext.LoadMask(Ext.getBody(), {
				id:'loading',
				msg : msg,
				removeMask : true
			}).show();
		}else{
			msgTip = new Ext.LoadMask(Ext.getBody(), {
				id:'loading',
				msg : '同步中...',
				removeMask : true
			}).show();
		}
		
	},
	hideLoad:function(){
		Ext.getCmp('loading').hide();
	},
	print:function(obj){
		console.log(obj);
	}

}

function alert1(obj) {
	var last = JSON.stringify(obj); // 将JSON对象转化为JSON字符
	alert(last);
}

/* 	 newCategory.save({
					 		success:function(a,opt){
					 			infoOpenForm.form.reset();
					 			//var data = Ext.JSON.decode(opt.response.responseText);
					 			//store.add(data.obj);
					 			store.load();
					 			msgTip.hide();
					 		},
					 		 failure: function(form, action) {
		                    	msgTip.hide();
		                        sysExt.alert(action.request.scope.reader.jsonData.msg);
		                    }
					 	});*/