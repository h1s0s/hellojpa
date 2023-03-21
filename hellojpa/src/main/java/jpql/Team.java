package jpql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

	@Id @GeneratedValue
	private long id;

	private String name;
	@OneToMany(mappedBy = "MEMBER_ID")
	private List<Member> member = new ArrayList<>();
}
