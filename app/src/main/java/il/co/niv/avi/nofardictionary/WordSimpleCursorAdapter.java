package il.co.niv.avi.nofardictionary;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Avi on 19/09/2015.
 */
public class WordSimpleCursorAdapter extends SimpleCursorAdapter {

    static class ViewHolder {
        int colId;
        View listTab;
    }

    public WordSimpleCursorAdapter (Context context, int layout, Cursor cursor
            , String[] from, int[] to, int flags) {
        super(context, layout, cursor, from, to, flags);
    }

    //override following 2 methods to use viewholder

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return super.newView(context, cursor, parent);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor);

        ViewHolder holder = (ViewHolder)view.getTag();
        if (holder == null) {
            holder = new ViewHolder();
            holder.colId = cursor.getColumnIndexOrThrow(WordDbAdapter.COL_ID);
            holder.listTab = view.findViewById(R.id.word_tab);
            view.setTag(holder);
        }

        if (cursor.getInt(holder.colId) > 0) {
            holder.listTab.setBackgroundColor(context.getResources().getColor(R.color.orange2));
        } else {
            holder.listTab.setBackgroundColor(context.getResources().getColor(R.color.green));
        }
    }
}
