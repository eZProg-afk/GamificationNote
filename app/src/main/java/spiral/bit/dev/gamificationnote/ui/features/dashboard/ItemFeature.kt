package spiral.bit.dev.gamificationnote.ui.features.dashboard

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import spiral.bit.dev.gamificationnote.common.adapters.ListItem
import spiral.bit.dev.gamificationnote.databinding.ItemDashboardFeatureBinding

data class ItemFeature(
    val title: String,
    val description: String
) : ListItem

fun featureDelegate(onClick: (ItemFeature) -> Unit) =
    adapterDelegateViewBinding<ItemFeature, ListItem, ItemDashboardFeatureBinding>({ inflater, container ->
        ItemDashboardFeatureBinding.inflate(inflater, container, false)
    }) {
        with(binding) {
            iconNextFrameLayout.setOnClickListener { onClick(item) }
            bind {
                featureTitleTextView.text = item.title
                featureDescriptionTextView.text = item.description
            }
        }
    }