use strict;
use warnings;

# day 4b - regex's are for winnies

my $good_strings = 0;
my $bad_strings = 0;

open (my $fh, '<', $ARGV[0]) or die "Error while opening file: $!\n";
# check each word/line
while(my $line = <$fh>) {
	chomp $line;
	my @line = split //, $line;
	print "$line ";
	my $fail = 1;
	# 1. check for non overlapping pair
	my $len = length($line);
	for (my $i=0; $i<$len-1; $i++) {
		for (my $j=$i+2; $j<$len-1; $j++) {
			if (($line[$i] eq $line[$j]) and ($line[$i+1] eq $line[$j+1])) {
				$fail = 0;
				last;
			}
		}
		last unless($fail);
	}

	# 2. check for repeaters
	unless($fail) {
		$fail = 1;
		for (my $i=0; $i<$len-2; $i++) {
			if ($line[$i] eq $line[$i+2]) {
				$fail = 0;
				last;
			}
		}
	}

	# summerize
	if ($fail) {
		$bad_strings++;
		print " is a naughty string\n";
	} else {
		$good_strings++;
		print " is a nice string\n";
	}
}

print "Total nice strings: $good_strings\n";
print "Total naughty strings: $bad_strings\n";
