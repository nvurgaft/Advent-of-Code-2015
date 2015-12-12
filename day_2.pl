use strict;
use warnings;

# day 2 - wrapping paper

print "Attempting to open file: $ARGV[0]\n";
open (my $FH, '<',$ARGV[0]) or die "Error opening file: $!\n";
my $total_paper = 0;
my $total_ribbon = 0;
while(<$FH>) {
	chomp;
	my ($a, $b, $c) = sort {$a <=> $b} split(/x/, $_);
	my @sides = ($a*$b, $b*$c, $c*$a); # are in orbit
	my $area = (2*$sides[0] + 2*$sides[1] + 2*$sides[2]);
	my $slack = $a*$b; 
	print "> $a $b $c\n";
	$total_paper+=($slack + $area);
	$total_ribbon+=(($a+$a+$b+$b) + ($a*$b*$c));
}

print "The elves need $total_paper feet of wrapping paper\n";
print "The elves need $total_ribbon feet of ribbon\n\n";
