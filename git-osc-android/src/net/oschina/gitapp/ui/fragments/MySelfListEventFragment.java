package net.oschina.gitapp.ui.fragments;

import java.util.List;

import android.util.Log;
import android.widget.BaseAdapter;
import net.oschina.gitapp.AppException;
import net.oschina.gitapp.R;
import net.oschina.gitapp.adapter.MySelfEventListAdapter;
import net.oschina.gitapp.adapter.MySelfListProjectAdapter;
import net.oschina.gitapp.bean.CommonList;
import net.oschina.gitapp.bean.Event;
import net.oschina.gitapp.bean.Project;
import net.oschina.gitapp.bean.MessageData;
import net.oschina.gitapp.ui.basefragment.BaseSwipeRefreshFragment;

/**
 * 个人最新动态列表Fragment
 * @created 2014-05-20 下午15:47
 * @author 火蚁（http://my.oschina.net/LittleDY）
 * 
 * 最后更新
 * 更新者
 */
public class MySelfListEventFragment extends BaseSwipeRefreshFragment<Event, CommonList<Event>> {
	
	public static MySelfListEventFragment newInstance() {
		return new MySelfListEventFragment();
	}
	
	@Override
	public BaseAdapter getAdapter(List<Event> list) {
		return new MySelfEventListAdapter(getActivity(), list, R.layout.myselfevent_listitem);
	}

	@Override
	public MessageData<CommonList<Event>> asyncLoadList(int page,
			boolean reflash) {
		MessageData<CommonList<Event>> msg = null;
		try {
			CommonList<Event> list = mApplication.getMySelfEvents(page, reflash);
			msg = new MessageData<CommonList<Event>>(list);
		} catch (AppException e) {
			e.makeToast(mApplication);
			e.printStackTrace();
			msg = new MessageData<CommonList<Event>>(e);
		}
		return msg;
	}
}