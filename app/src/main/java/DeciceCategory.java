import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DeciceCategory extends BaseAdapter {
    Context context;
    private String [] categories;
    private int [] images;

    public DeciceCategory(Context context, String[] categories, int[] images) {
        this.context = context;
        this.categories = categories;
        this.images = images;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    private static class ViewHolder {

        TextView name;
        ImageView icon;

    }
}
