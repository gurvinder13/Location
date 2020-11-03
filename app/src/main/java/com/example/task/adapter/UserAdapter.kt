import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task.R
import kotlinx.android.synthetic.main.rv_item_layout.view.*

class UserAdapter(
    val userList: List<Users>,
    val clickItemListener: ClickItemListener
) : RecyclerView.Adapter<UserAdapter.NameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val li = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = li.inflate(R.layout.rv_item_layout, parent, false)
        return NameViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        val user = userList[position]
        holder.itemView.tv_institute_name.text = user.Institution_Name
        holder.itemView.tv_poc_name.text = user.POC_Name
        holder.itemView.tv_employee_id.text = user.marketing_user_employee_id

        holder.itemView.setOnClickListener {
            clickItemListener.onClick(0, holder.adapterPosition)
        }

    }

    class NameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}