use strict;
use warnings;

# day 1 - count closures

open(my $fh, '<', $ARGV[0]) or die "Error while opening file: $!\n";

my $floor = 0;
my $whenBasement = -1;
my $entered = 0;
while(my $line = <$fh>) {
	chomp $line;
	my @letters = split //, $line;
	for my $i(0..@letters-1) {
		--($floor) if ($letters[$i] eq ')');
		++($floor) if ($letters[$i] eq '(');
			print "($i) at floor $floor\n";
		if ($floor==-1 and $entered==0) {
			$whenBasement = $i+1;
			$entered = 1;
		}
	}
	sleep(1);
}
print "santa is on floor: $floor\n";
print "he first entered the basement at position: $whenBasement\n\n";
