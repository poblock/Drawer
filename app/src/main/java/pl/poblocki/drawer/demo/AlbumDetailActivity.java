package pl.poblocki.drawer.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import pl.poblocki.drawer.R;

public class AlbumDetailActivity extends AppCompatActivity {

  static final String EXTRA_ALBUM = AlbumDetailActivity.class.getPackage() + ".extra.ALBUM";

  private Album album;
  private View mShareButton;

  public static Intent makeIntent(Context context, Album album) {
    Intent intent = new Intent(context, AlbumDetailActivity.class);
    intent.putExtra(EXTRA_ALBUM, album);
    return intent;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.demo_activity_detail);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    album = (Album) getIntent().getExtras().getSerializable(EXTRA_ALBUM);

    CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
    collapsingToolbarLayout.setTitle(album.name);

    ImageView pictureView = (ImageView) findViewById(R.id.picture);
    Glide.with(this).load(album.thumbnail).into(pictureView);

    mShareButton = findViewById(R.id.share_button);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == android.R.id.home) {
      onBackPressed();
      return true;
    }
    return super.onOptionsItemSelected(item);

  }

  public void shareClick(View view) {;
  }
}