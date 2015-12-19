use strict;
use warnings;

# day 8b - counting letters

open (my $fh, '<', $ARGV[0]) or die "Error opening file: $!\n";

my ($total_chars, $total_are) = (0, 0);
foreach my $line (<$fh>) {
	chomp $line;
	my $abs_len = length($line);
	my @letters = split //, $line;

	my $new_word = "\"";
	for (my $i=0; $i < @letters; ++$i) {		
		if ($letters[$i] eq '"') {
			$new_word.= "\\\"";
		} elsif ($letters[$i] eq '\'') {
			$new_word.= "\\\'";
		} elsif ($letters[$i] eq '\\') {
			$new_word.= "\\\\";
		} else {
			$new_word.=$letters[$i];
		}
	}
	$new_word.="\"";
	$total_chars += $abs_len;
	$total_are += length($new_word);
}

my $result = $total_are - $total_chars;
print "$result\n";
