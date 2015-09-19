package my_tag;

import java.io.IOException;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

@SuppressWarnings("serial")
public class InputTable extends BodyTagSupport {
  private int x_start;
  private int y_start;
  

  
public int getX_start() {
	return x_start;
}



public void setX_start(int x_start) {
	this.x_start = x_start;
}



public int getY_start() {
	return y_start;
}



public void setY_start(int y_start) {
	this.y_start = y_start;
}



@SuppressWarnings({ })
public int doStartTag() throws JspTagException {
	try {
		pageContext.getOut().write("<TABLE>");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	int number_input_form = 0;
	for (int i = 1; i <= x_start; i++) {
		try {
			pageContext.getOut().write("<TR>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int j = 1; j <= y_start; j++){
			number_input_form = number_input_form +1;
			try {
			  pageContext.getOut().write("<TD>");
			  pageContext.getOut().write("<input type='text' name='number_input_form"+number_input_form+"' value='' size='2'/>");
			  pageContext.getOut().write("</TD>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			pageContext.getOut().write("</TR>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	try {
		pageContext.getOut().write("</TABLE>");
	} catch (IOException e) {
		e.printStackTrace();
	}
	return SKIP_BODY;
}
}
