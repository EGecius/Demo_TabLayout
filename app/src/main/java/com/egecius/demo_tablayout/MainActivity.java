package com.egecius.demo_tablayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	private ViewPager viewPager;
	private TabLayout tabLayout;

	private String[] titles = {"Section 1", "Section 2", "Section 3"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initToolbar();
		initTabs();
		initViewPager();
	}

	private void initViewPager() {
		SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		viewPager = (ViewPager) findViewById(R.id.container);
		viewPager.setAdapter(mSectionsPagerAdapter);

		viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
		tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				viewPager.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {
			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {
			}
		});
	}

	private void initToolbar() {
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
	}

	private void initTabs() {
		tabLayout = (TabLayout) findViewById(R.id.tab_layout);
		tabLayout.addTab(tabLayout.newTab().setText(titles[0]));
		tabLayout.addTab(tabLayout.newTab().setText(titles[1]));
		tabLayout.addTab(tabLayout.newTab().setText(titles[2]));
		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		public PlaceholderFragment() {}

		/**
		 * Returns a new instance of this fragment for the given section
		 * number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			TextView textView = (TextView) rootView.findViewById(R.id.section_label);
			textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
			return rootView;
		}
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class below).
			return PlaceholderFragment.newInstance(position + 1);
		}

		@Override
		public int getCount() {
			return titles.length;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
				case 0:
					return titles[0];
				case 1:
					return titles[1];
				case 2:
					return titles[2];
			}
			return null;
		}
	}
}
