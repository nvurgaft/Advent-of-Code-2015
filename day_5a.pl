use strict;
use warnings;

# day 4 - too lazy for regex's

my @vowels = split //, "aeiou";
my @bad_substrings = qw(ab cd pq xy);
my $good_strings = 0;
my $bad_strings = 0;

open my $fh, '<', $ARGV[0];
# check each word/line
while(my $line = <$fh>) {
	chomp $line;
	print "$line ";
	my $fail = 0;
	# 1. check for bad strings
	foreach my $bs(@bad_substrings) {
		if (index($line, $bs)>=0) {
			print " :: found $bs :: "; 
			$fail = 1;
			last;
		}
	}

	# 2. check for doubles
	unless($fail) {
		my @letters = split //, $line;
		for my $i(1..@letters-1) {
			if ($letters[$i] ne $letters[$i-1]) {
				$fail=1;
			} else {
				$fail=0;
				print " :: $letters[$i] $letters[$i-1] :: "; 
				last;
			}
		}
	}

	# 3. check vowels
	unless($fail) {
		my $amount = 0;
		my @splat = split //, $line;
		foreach my $vowel(@vowels) {	
			foreach(@splat) {
				if ($_ eq $vowel) {
					++$amount;
				}
			}
		}
		print " :: amount: $amount :: ";
		if ($amount >= 3) {
			$fail=0;
		} else {
			$fail=1;
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
