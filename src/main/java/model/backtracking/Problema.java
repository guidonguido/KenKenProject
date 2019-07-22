package model.backtracking;

public abstract class Problema<P, S> {
	protected abstract P primoPuntoDiScelta();

	protected abstract P prossimoPuntoDiScelta(P ps, S s);

	protected abstract P ultimoPuntoDiScelta();

	protected abstract S primaScelta(P ps);

	protected abstract S prossimaScelta(S s);

	protected abstract S ultimaScelta(P ps);

	protected abstract boolean assegnabile(S scelta, P puntoDiScelta);

	protected abstract void assegna(S scelta, P puntoDiScelta);

	protected abstract void deassegna(S scelta, P puntoDiScelta);

	protected abstract P precedentePuntoDiScelta(P puntoDiScelta);

	protected abstract S ultimaSceltaAssegnata(P puntoDiScelta);

	protected abstract void scriviSoluzione(int nrsol);

	private int nummaxsoluzioni, nrsoluzione = 0;

	public Problema(int nummaxsoluzioni) {
		this.nummaxsoluzioni = nummaxsoluzioni;
	}

	public Problema() {
		this(Integer.MAX_VALUE);
	}

	public void risolvi() {
		System.out.println("Chiamato il metodo Risolvi");
		nrsoluzione = 0;
		P ps = primoPuntoDiScelta();
		S s = primaScelta(ps);

		boolean backtrack = false, fine = false;
		do {			
			while (!backtrack && nrsoluzione < nummaxsoluzioni) {
				
				if (assegnabile(s, ps)) {
					assegna(s, ps);
					if (ps.equals(ultimoPuntoDiScelta())) {
						++nrsoluzione;
						scriviSoluzione(nrsoluzione);
						deassegna(s, ps);
						if (!s.equals(ultimaScelta(ps)))
							s = prossimaScelta(s);
						else
							backtrack = true;

					} else {
						ps = prossimoPuntoDiScelta(ps,s);
						s = primaScelta(ps);

					}
				} else if (!s.equals(ultimaScelta(ps)))
					s = prossimaScelta(s);
				else
					backtrack = true;
			}
			fine = ps.equals(primoPuntoDiScelta())
					|| nrsoluzione == nummaxsoluzioni;
			while (backtrack && !fine) {
				ps = precedentePuntoDiScelta(ps);
				s = ultimaSceltaAssegnata(ps);
				deassegna(s, ps);
				if (!s.equals(ultimaScelta(ps))) {
					s = prossimaScelta(s);
					backtrack = false;
				} else if (ps.equals(primoPuntoDiScelta()))
					fine = true;
			}

		} while (!fine);
	}
}
