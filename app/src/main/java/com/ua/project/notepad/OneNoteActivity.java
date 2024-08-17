package com.ua.project.notepad;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import android.view.MenuItem;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;

import com.ua.project.notepad.databinding.ActivityOneNoteBinding;

public class OneNoteActivity extends AppCompatActivity {
    ActivityOneNoteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityOneNoteBinding.inflate(getLayoutInflater());

        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding.notepadTextMultiLine.setText(new SpannableString(""));

        binding.textColorButton.setOnLongClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(OneNoteActivity.this, v);

            popupMenu.inflate(R.menu.text_color_picker_menu);
            popupMenu.setOnMenuItemClickListener(item -> {
                int itemId = item.getItemId();

                if(itemId == R.id.defaultTextMenuItem) {
                    binding.textColorButton.setTag(R.color.defaultTextColor);
                    binding.textColorButton.setBackgroundColor(getColor(R.color.defaultBackgroundColor));
/*
                    Spannable editableText = binding.notepadTextMultiLine.getText();

                    ForegroundColorSpan[] spans = editableText.getSpans(
                            binding.notepadTextMultiLine.getSelectionStart(),
                            binding.notepadTextMultiLine.getSelectionEnd(),
                            ForegroundColorSpan.class
                    );

                    Arrays.stream(spans).forEach(editableText::removeSpan);
*/
                }
                else if(itemId == R.id.blueTextMenuItem) {
                    binding.textColorButton.setTag(R.color.textBlue);
                    binding.textColorButton.setBackgroundColor(getColor(R.color.textBlue));
                }
                else if(itemId == R.id.greenTextMenuItem) {
                    binding.textColorButton.setTag(R.color.textGreen);
                    binding.textColorButton.setBackgroundColor(getColor(R.color.textGreen));
                }

                return true;
            });

            popupMenu.show();
            return true;
        });

        binding.textColorButton.setOnClickListener(v -> {
            if(binding.textColorButton.getTag() != null) {
                int textColorId = (int) binding.textColorButton.getTag();
                Spannable editableText = (Spannable) binding.notepadTextMultiLine.getText();
                int startSelection = binding.notepadTextMultiLine.getSelectionStart();
                int endSelection = binding.notepadTextMultiLine.getSelectionEnd();

                if(textColorId == R.color.defaultTextColor) {
                    removeTextColorFromSpanText(editableText, startSelection, endSelection);
                }
                else {
                    editableText.setSpan(
                            new ForegroundColorSpan(getColor(textColorId)),
                            startSelection,
                            endSelection,
                            Spanned.SPAN_INCLUSIVE_INCLUSIVE
                    );
                }
            }

        });

        binding.backgroundColorButton.setOnLongClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(OneNoteActivity.this, v);

            popupMenu.inflate(R.menu.background_color_picker_menu);
            popupMenu.setOnMenuItemClickListener(item -> {
                int itemId = item.getItemId();

                if(itemId == R.id.defaultBackgroundMenuItem) {
                    binding.backgroundColorButton.setTag(R.color.defaultBackgroundColor);
                    binding.backgroundColorButton.setBackgroundColor(getColor(R.color.defaultBackgroundColor));
                }
                else if(itemId == R.id.yellowBackgroundMenuItem) {
                    binding.backgroundColorButton.setTag(R.color.backgroundYellow);
                    binding.backgroundColorButton.setBackgroundColor(getColor(R.color.backgroundYellow));
                }
                else if(itemId == R.id.blackBackgroundMenuItem) {
                    binding.backgroundColorButton.setTag(R.color.backgroundBlack);
                    binding.backgroundColorButton.setBackgroundColor(getColor(R.color.backgroundBlack));
                }
                else if(itemId == R.id.mintBackgroundMenuItem) {
                    binding.backgroundColorButton.setTag(R.color.backgroundMint);
                    binding.backgroundColorButton.setBackgroundColor(getColor(R.color.backgroundMint));
                }

                return true;
            });

            popupMenu.show();
            return true;
        });

        binding.backgroundColorButton.setOnClickListener(v -> {
            if(binding.backgroundColorButton.getTag() != null) {
                int colorId = (int) binding.backgroundColorButton.getTag();
                Spannable editableText = (Spannable) binding.notepadTextMultiLine.getText();
                int startSelection = binding.notepadTextMultiLine.getSelectionStart();
                int endSelection = binding.notepadTextMultiLine.getSelectionEnd();

                if(colorId == R.color.defaultBackgroundColor) {
                    removeBackgroundColorFromSpanText(editableText, startSelection, endSelection);
                }
                else {
                    editableText.setSpan(
                            new BackgroundColorSpan(getColor(colorId)),
                            startSelection,
                            endSelection,
                            Spanned.SPAN_INCLUSIVE_INCLUSIVE
                    );
                }
            }
        });

        binding.textStyleButton.setOnLongClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(OneNoteActivity.this, v);

            popupMenu.inflate(R.menu.text_style_picker_menu);
            popupMenu.setOnMenuItemClickListener(item -> {
                int itemId = item.getItemId();
                Spannable buttonText = new SpannableString(binding.textStyleButton.getText());

                removeStylesFromSpanText(buttonText, 0, buttonText.length());

                if(itemId == R.id.defaultTextStyleMenuItem) {
                    binding.textStyleButton.setTag("default");
                }
                else if(itemId == R.id.boldTextMenuItem) {
                    buttonText.setSpan(
                            new StyleSpan(Typeface.BOLD),
                            0,
                            binding.textStyleButton.length(),
                            Spanned.SPAN_INCLUSIVE_INCLUSIVE
                    );

                    binding.textStyleButton.setTag("bold");
                }
                else if(itemId == R.id.italicTextMenuItem) {
                    buttonText.setSpan(
                            new StyleSpan(Typeface.ITALIC),
                            0,
                            binding.textStyleButton.length(),
                            Spanned.SPAN_INCLUSIVE_INCLUSIVE
                    );

                    binding.textStyleButton.setTag("italic");
                }
                else if(itemId == R.id.underlineTextMenuItem) {
                    buttonText.setSpan(
                            new UnderlineSpan(),
                            0,
                            binding.textStyleButton.length(),
                            Spanned.SPAN_INCLUSIVE_INCLUSIVE
                    );

                    binding.textStyleButton.setTag("underline");
                }

                binding.textStyleButton.setText(buttonText);
                return true;
            });

            popupMenu.show();
            return true;
        });

        binding.textStyleButton.setOnClickListener(v -> {
            String buttonTag = binding.textStyleButton.getTag() instanceof String ? String.valueOf(binding.textStyleButton.getTag()) : null;

            if(buttonTag != null) {
                Spannable editableText = (Spannable) binding.notepadTextMultiLine.getText();
                int startPosition = binding.notepadTextMultiLine.getSelectionStart();
                int endPosition = binding.notepadTextMultiLine.getSelectionEnd();

                switch (buttonTag) {
                    case "default":
                        removeStylesFromSpanText(editableText, startPosition, endPosition);
                        break;
                    case "bold":
                        editableText.setSpan(
                                new StyleSpan(Typeface.BOLD),
                                startPosition,
                                endPosition,
                                Spanned.SPAN_INCLUSIVE_INCLUSIVE
                        );
                        break;
                    case "italic":
                        editableText.setSpan(
                                new StyleSpan(Typeface.ITALIC),
                                startPosition,
                                endPosition,
                                Spanned.SPAN_INCLUSIVE_INCLUSIVE
                        );
                        break;
                    case "underline":
                        editableText.setSpan(
                                new UnderlineSpan(),
                                startPosition,
                                endPosition,
                                Spanned.SPAN_INCLUSIVE_INCLUSIVE
                        );
                        break;
                }
            }
        });
    }

    private void removeStylesFromSpanText(Spannable text, int start, int end) {
        StyleSpan[] styleSpans = text.getSpans(start, end, StyleSpan.class);
        UnderlineSpan[] underlineSpans = text.getSpans(start, end, UnderlineSpan.class);

        for (StyleSpan span : styleSpans) {
            int currentStyle = span.getStyle();
            int spanStart = text.getSpanStart(span);
            int spanEnd = text.getSpanEnd(span);

            text.removeSpan(span);

            if(spanStart < start) {
                text.setSpan(
                        new StyleSpan(currentStyle),
                        spanStart,
                        start,
                        Spanned.SPAN_INCLUSIVE_INCLUSIVE
                );
            }

            if(spanEnd > end) {
                text.setSpan(
                        new StyleSpan(currentStyle),
                        end,
                        spanEnd,
                        Spanned.SPAN_INCLUSIVE_INCLUSIVE
                );
            }
        }

        for (UnderlineSpan span : underlineSpans) {
            int spanStart = text.getSpanStart(span);
            int spanEnd = text.getSpanEnd(span);

            text.removeSpan(span);

            if(spanStart < start) {
                text.setSpan(
                        new UnderlineSpan(),
                        spanStart,
                        start,
                        Spanned.SPAN_INCLUSIVE_INCLUSIVE
                );
            }

            if(spanEnd > end) {
                text.setSpan(
                        new UnderlineSpan(),
                        end,
                        spanEnd,
                        Spanned.SPAN_INCLUSIVE_INCLUSIVE
                );
            }
        }
    }

    private void removeBackgroundColorFromSpanText(Spannable text, int start, int end) {
        BackgroundColorSpan[] spans = text.getSpans(start, end, BackgroundColorSpan.class);

        for (BackgroundColorSpan span : spans) {
            int backgroundColorId = span.getBackgroundColor();
            int spanStart = text.getSpanStart(span);
            int spanEnd = text.getSpanEnd(span);

            text.removeSpan(span);

            if(spanStart < start) {
                text.setSpan(
                        new BackgroundColorSpan(backgroundColorId),
                        spanStart,
                        start,
                        Spanned.SPAN_INCLUSIVE_INCLUSIVE
                );
            }

            if(spanEnd > end) {
                text.setSpan(
                        new BackgroundColorSpan(backgroundColorId),
                        end,
                        spanEnd,
                        Spanned.SPAN_INCLUSIVE_INCLUSIVE
                );
            }
        }
    }

    private void removeTextColorFromSpanText(Spannable text, int start, int end) {
        ForegroundColorSpan[] spans = text.getSpans(start, end, ForegroundColorSpan.class);

        for (ForegroundColorSpan span : spans) {
            int textColorId = span.getForegroundColor();
            int spanStart = text.getSpanStart(span);
            int spanEnd = text.getSpanEnd(span);

            text.removeSpan(span);

            if(spanStart < start) {
                text.setSpan(
                        new ForegroundColorSpan(textColorId),
                        spanStart,
                        start,
                        Spanned.SPAN_INCLUSIVE_INCLUSIVE
                );
            }

            if(spanEnd > end) {
                text.setSpan(
                        new ForegroundColorSpan(textColorId),
                        end,
                        spanEnd,
                        Spanned.SPAN_INCLUSIVE_INCLUSIVE
                );
            }
        }
    }

}