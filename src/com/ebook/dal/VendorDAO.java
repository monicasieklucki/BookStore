package com.ebook.dal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ebook.model.vendor.Vendor;
import com.ebook.model.vendor.VendorLine;
import com.ebook.model.item.Product;

public class VendorDAO {
	private ProductDAO prodDAO = new ProductDAO();

	public VendorDAO() {};

	/**
	 * Returns a vendor with an updated list of products
	 * @param vendorid Id of the vendor to look up
	 * @return Vendor
	 */
	public Vendor getVendor(Integer vendorid) {
		try {
			// Get vendor
			Statement st = DBHelper.getConnection().createStatement();
			String selectVendorQuery = "SELECT vendorid, vendorname FROM vendor WHERE vendorid = " + vendorid + ";";

			ResultSet vendorRS = st.executeQuery(selectVendorQuery);
			System.out.println("vendorDAO: *************** Query " + selectVendorQuery);

			// Get vendor
			Vendor vendor = new Vendor();
			while (vendorRS.next()) {
				vendor.setVendorId(vendorRS.getInt("vendorid"));
				vendor.setVendorName(vendorRS.getString("vendorname"));
			}
			// close to manage resources
			vendorRS.close();
			
			getAllVendorProducts(vendor);

			return vendor;
		} catch (SQLException se) {
			System.err.println("vendorDAO: Threw a SQLException retrieving the vendor object.");
			System.err.println(se.getMessage());
			se.printStackTrace();
		}

		return null;
	}
	
	/**
	 * Returns all vendors
	 * Vendor objects do not include vendorLines
	 * @return A Set of all vendors without products offered
	 */
	public Set<Vendor> getVendors() {
		Set<Vendor> vendors = new HashSet<Vendor>();
		String selectStatement = "SELECT vendorId, vendorname FROM vendor";
		try ( Connection con = DBHelper.getConnection();
				PreparedStatement st = con.prepareStatement(selectStatement);) {
			ResultSet vendorsRs = st.executeQuery();
			
			while(vendorsRs.next()) {
				Vendor vendor = new Vendor();
				vendor.setVendorId(vendorsRs.getInt("vendorid"));
				vendor.setVendorName(vendorsRs.getString("vendorname"));
				vendors.add(vendor);			
			}
		} catch (SQLException se) {
			System.err.println("VendorDAO: Threw a SQL Exception retrieving all vendors.");
			System.err.println(se.getMessage());
			se.printStackTrace();
		}
		return vendors;
	}

	/**
	 * Adds a vendor to the DB
	 * @param vendor Vendor to be added
	 * @throws SQLException
	 */
	public void addVendor(Vendor vendor) throws SQLException {
		String insertStm = "INSERT INTO Vendor (vendorname) VALUES (?)";
		try (Connection con = DBHelper.getConnection();
				PreparedStatement statement = con.prepareStatement(insertStm, Statement.RETURN_GENERATED_KEYS);) {
			statement.setString(1, vendor.getVendorName());
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("Creating vendor failed, no rows affected.");
			}

			try (ResultSet generatedId = statement.getGeneratedKeys()) {
				if (generatedId.next()) {
					vendor.setVendorId(generatedId.getInt(1));
				} else {
					throw new SQLException("Creating vendor failed, no ID obtained.");
				}
			}
		} catch (SQLException se) {
			System.err.println(se.getMessage());
			se.printStackTrace();
		}
	}

	/**
	 * Removed a vendor for the given ID
	 * @param vendorid ID of vendor to remove
	 */
	public void removeVendor(Integer vendorid) {
		String deleteVendorLineQuery = "DELETE FROM vendorline WHERE vendorid = " + vendorid + ";";
		String deleteVendorQuery = "DELETE FROM vendor WHERE vendorid = " + vendorid + ";";
		try (Statement st = DBHelper.getConnection().createStatement();) {
			// Delete Vendor Line
			st.executeUpdate(deleteVendorLineQuery);
			System.out.println("VendorDAO: *************** Query " + deleteVendorLineQuery);

			// Delete Vendor
			st.executeUpdate(deleteVendorQuery);
			System.out.println("VendorDAO: *************** Query " + deleteVendorQuery);
		} catch (SQLException ex) {
			System.err.println("VendorDAO: Threw a SQLException deleting the vendor object.");
			System.err.println(ex.getMessage());
		}
	}

	/**
	 * Creates an association between a vendor and a product The default quantity of
	 * 1 for is hard coded at this time.
	 * @param vendor  The vendor the product should belong to
	 * @param product The product to associate.
	 */
	public void addVendorProduct(Vendor vendor, Product product) {
		String insertStm = "INSERT INTO vendorline (productid, vendorid, quantity) VALUES (?, ?, 1)";
		try (Connection con = DBHelper.getConnection();
				PreparedStatement statement = con.prepareStatement(insertStm, Statement.RETURN_GENERATED_KEYS);) {
			statement.setInt(1, product.getId());
			statement.setInt(2, vendor.getVendorId());
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("Creating vendorLine failed, Product not associated to vendor.");
			}
		} catch (SQLException se) {
			System.err.println(se.getMessage());
			se.printStackTrace();
		}
	}

	/**
	 * Returns a list of all vendor products and quantities. Updates the vendor with this latest list
	 * @param vendor the vendor to look up products for
	 * @return returns a list of vendorLines, also updates the vendor object.
	 */
	public List<VendorLine> getAllVendorProducts(Vendor vendor) {
		String selectVendorLineQuery = "SELECT productId, quantity FROM vendorline where vendorid = "
				+ vendor.getVendorId() + ";";
		try (Statement st = DBHelper.getConnection().createStatement();
				ResultSet vendorLineRS = st.executeQuery(selectVendorLineQuery);) {
			System.out.println("vendorDAO: *************** Query " + selectVendorLineQuery);
			List<VendorLine> vendorProducts = new ArrayList<VendorLine>();
			while (vendorLineRS.next()) {
				VendorLine vendorLine = new VendorLine();
				Product product = prodDAO.getProduct(vendorLineRS.getInt(1));
				vendorLine.setProduct(product);
				vendorLine.setQuantity(vendorLineRS.getInt(2));
				vendorProducts.add(vendorLine);
			}
			vendor.setVendorLines(vendorProducts);
			return vendorProducts;
		} catch (SQLException se) {
			System.err.println("vendorDAO: Threw a SQLException retrieving the vendor object.");
			System.err.println(se.getMessage());
			se.printStackTrace();
		}
		return null;
	}

	/**
	 * Deletes a vendorLine for the given vendorId and productId
	 * @param vendorId id of vendor who owns the vendorLine
	 * @param productId product to be deleted for that vendor
	 */
	public void removeVendorLine(Integer vendorId, Integer productId) {
		String deleteVendorLineQuery = "DELETE FROM vendorline WHERE vendorid = " + vendorId + ";";
		try (Statement st = DBHelper.getConnection().createStatement();) {
			// Delete Vendor Line
			st.executeUpdate(deleteVendorLineQuery);
			System.out.println("VendorDAO: *************** Query " + deleteVendorLineQuery);
		} catch (SQLException ex) {
			System.err.println("VendorDAO: Threw a SQLException deleting the vendorLine object.");
			System.err.println(ex.getMessage());
		}
		
	}
}
