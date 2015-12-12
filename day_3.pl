use strict;
use warnings;

# day 3 - santa delivery service

my $file = $ARGV[0];
open(my $fh, '<', $file) or die "Error while opening file: $!\n";
my $beenTo = 1;
my @visited = ();
my @santa_p = (0, 0); # cartesian x, y
my @robo_p = (0, 0);
push @visited, \@robo_p;
push @visited, \@santa_p;
my @santa_pos = @santa_p;
my @robo_pos = @robo_p;

my @input = split //, <$fh>;
my $len = @input;
for my $i (0..$len-1) {

	# move santa (or robo-santa) around the world
	if ($i%2) {
		# santa's turn
		if ($input[$i] eq '<') {
			--$santa_pos[0];
		} elsif ($input[$i] eq '>') {
			++$santa_pos[0];
		} elsif ($input[$i] eq '^') {
			++$santa_pos[1];
		} elsif ($input[$i] eq 'v') {
			--$santa_pos[1];
		}
	} else {
		# robo-santa's turn
		if ($input[$i] eq '<') {
			--$robo_pos[0];
		} elsif ($input[$i] eq '>') {
			++$robo_pos[0];
		} elsif ($input[$i] eq '^') {
			++$robo_pos[1];
		} elsif ($input[$i] eq 'v') {
			--$robo_pos[1];
		}
	}

	# traverse the positions already visited 
	my $wasVisited = 0;	
	foreach (@visited) {
		#print "visited node: @$_\n";
		my ($x, $y) = @$_;
		if ($i%2) {
			if ($santa_pos[0] eq $x && $santa_pos[1] eq $y) {
				$wasVisited = 1;
			}
		} else {
			if ($robo_pos[0] eq $x && $robo_pos[1] eq $y) {
				$wasVisited = 1;
			}
		}
	}

	unless ($wasVisited) {
		++$beenTo;
		my @newPos;
		if ($i%2) {
			@newPos = @santa_pos;
		} else {
			@newPos = @robo_pos;
		}
		push @visited, \@newPos;
	}
}

print "Santa and Robo-santa have been to $beenTo houses\n\n";
