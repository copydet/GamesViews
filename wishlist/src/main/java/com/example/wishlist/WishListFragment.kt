package com.example.wishlist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.core.ui.WishListAdapter
import com.example.gamesviews.ui.detail.DetailActivity
import com.example.wishlist.databinding.FragmentWishListBinding
import com.example.wishlist.di.wishListModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class WishListFragment : Fragment() {
    private val wishlistViewModel: WishListViewModel by viewModel()
    private lateinit var adapter: WishListAdapter
    private var _binding: FragmentWishListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loadKoinModules(wishListModule)
        _binding = FragmentWishListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = WishListAdapter()
        binding.rvWishlistGames.adapter = adapter
        wishlistViewModel.gamesWishList.observe(viewLifecycleOwner, {
            if (it != null){
                if(it.size > 0){
                    adapter.submitList(it)
                    adapter.notifyDataSetChanged()
                    binding.rvWishlistGames.visibility = View.VISIBLE
                }
            }
        })
        adapter.onItemClick = {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, it)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}