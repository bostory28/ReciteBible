package server;

import java.io.Serializable;
import java.util.ArrayList;

import vo.VersesVO;

public class ArraylistSerialized implements Serializable {
	public ArrayList<VersesVO> list = null;

	public ArraylistSerialized(ArrayList<VersesVO> list) {
		this.list = list;
	}
}
