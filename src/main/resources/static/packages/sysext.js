/**
 * extjs 页面自适应
 */
var winWidth = 0;
var winHeight = 0;
var panel = null;
function findDimensions() // 函数：获取尺寸
{
	// 获取窗口宽度
	if (window.innerWidth)
		winWidth = window.innerWidth;
	else if ((document.body) && (document.body.clientWidth))
		winWidth = document.body.clientWidth;
	// 获取窗口高度
	if (window.innerHeight)
		winHeight = window.innerHeight;
	else if ((document.body) && (document.body.clientHeight))
		winHeight = document.body.clientHeight;
	// 通过深入Document内部对body进行检测，获取窗口大小
	if (document.documentElement && document.documentElement.clientHeight
			&& document.documentElement.clientWidth) {
		winHeight = document.documentElement.clientHeight;
		winWidth = document.documentElement.clientWidth;
	}

}
function initSize(p) {
	if (p != null) {
		findDimensions();
		setPanel(p);
		panel.setSize(winWidth, winHeight);
	}

}
function setPanel(p) {
	if (panel == null) {
		panel = p;
	}
}
window.onresize = initSize;
