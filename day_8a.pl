use strict;
use warnings;

# day 8 - counting letters

open (my $fh, '<', $ARGV[0]) or die "Error opening file: $!\n";

my ($total_chars, $total_are) = (0, 0);
foreach (<$fh>) {
	chomp;
	my $abs_len = length($_);
	my @letters = split //, $_;
	
	my $len = @letters;
	if ($letters[0] eq $letters[$len-1] and $letters[0] eq '"') {
		my $count = 0;
		for (my $i=1; $i < $len-1; ++$i) {
			if ($letters[$i] eq '\\') {
				$i+=2 if ($letters[$i+1] eq 'x');
				++$i;
			}
			++$count;
		}
		$total_chars += $abs_len;
		$total_are += $count;
	}
}

my $result = $total_chars - $total_are;
print "$result\n";
