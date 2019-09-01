/**
 * @class Ext.chooser.IconBrowser
 * @extends Ext.view.View
 * @author Ed Spencer
 * 
 * This is a really basic subclass of Ext.view.View. All we're really doing here is providing the template that dataview
 * should use (the tpl property below), and a Store to get the data from. In this case we're loading data from a JSON
 * file over AJAX.
 */
Ext.define('Ext.chooser.IconBrowser', {
    extend: 'Ext.view.View',
    alias: 'widget.iconbrowser',
    
    uses: 'Ext.data.Store',
    
	singleSelect: true,
    overItemCls: 'x-view-over',
    itemSelector: 'div.thumb-wrap',
    tpl: [
        // '<div class="details">',
            '<tpl for=".">',
                '<div class="thumb-wrap">',
                    '<div class="thumb">',
                    (!Ext.isIE6? '<img src="img-icon/1.png" width="30px" height="30px"/>' : 
                    '<div style="width:74px;height:74px;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src=\'{thumb}\')"></div>'),
                    '</div>',
                    '<span>{resourcename}</span>',
                '</div>',
            '</tpl>'
        // '</div>'
    ],
    
    initComponent: function() {
        this.store = Ext.create('Ext.data.Store', {
            fields: ['resourcename', 'resourceurl', 'content','img']
        });
        
        this.callParent(arguments);
        //this.store.sort();
    }
});