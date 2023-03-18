# TextOnImageSpan
+ 继承自ImageSpan，支持在ImageSpan上绘制你想要的文本。重写了getSize方法，根据设置的文本与图像之间的最大宽高计算出span最终的尺寸。重写了draw方法，修改了原有的部分图片绘制逻辑，然后再加入了文本的绘制逻辑。<br>
+ 图片的垂直方向位置计算继承了原有ImageSpan的计算规则，而水平方向暂时默认居中绘制，不支持修改。而文本的位置则是相对于图片的位置居中，另外还提供了**mOffsetX**和**mOffsetY**两个参数，允许使用者对文本的绘制位置进行自定义的偏移(**在相对于图片位置居中的前提下**)。


## screenshot

![image](https://user-images.githubusercontent.com/51867928/226116662-41cb50e3-46f7-4a1b-9af7-3311e904d275.png)

